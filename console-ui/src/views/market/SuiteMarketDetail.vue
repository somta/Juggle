<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { useRoute,useRouter } from 'vue-router';
import {orderService, suiteMarketService} from '@/service';
import {CreateOrder, SuiteMarketInfo} from '@/typings';
import { ElMessage } from 'element-plus';
import QRCode from 'qrcode'
import UserAgreement from '../common/UserAgreement.vue'

const route = useRoute();
const router = useRouter();
let paramsData = reactive({
  params: route.params,
});
const suiteMarketInfo = ref<SuiteMarketInfo>({
  id: null,
  suiteCode: '',
  suiteName: '',
  suiteImage: '',
  suiteDesc: '',
  priceStatus: 0,
  suitePrice: 0,
  suiteHelpDocJson: '',
  installStatus: false,
  apiList: []
});
const suiteHelpDocList = ref([]);
const orderDetailDialogVisible = ref(false);
const userAgreementCheck = ref(false);
const userAgreementRef = ref();
const payDialogVisible = ref(false);
const payQrCode = ref();
const createOrder = ref<CreateOrder>({
  orderNo: '',
  qrCode:''
});

querySuiteMarketInfo();

async function handleInstallSuiteMarket() {
  if(suiteMarketInfo.value.priceStatus == 1 && suiteMarketInfo.value.suitePrice > 0){
    orderDetailDialogVisible.value = true;
  } else {
    await installSuiteMarket();
  }
}

function openUserAgreement(){
  if (userAgreementRef.value) {
    userAgreementRef.value.openUserAgreementDialog();
  }
}

async function handleBuySuiteMarket() {
  if(!userAgreementCheck.value){
    ElMessage({ type: 'error', message: '请先勾选用户协议' });
    return;
  }
  await createSuiteOrder();
}

let timerId;
async function createSuiteOrder() {
  let suiteId = Number(paramsData.params.suiteId);
  const res = await orderService.createOrder({
    orderName: suiteMarketInfo.value.suiteName + "套件",
    orderType: 1,
    goodsId: suiteId,
  });
  if (res.success) {
    createOrder.value = res.result;
    QRCode.toDataURL(res.result.qrCode)
        .then(code => {
          payQrCode.value = code;
        }).catch(err => {
          console.error(err)
        })
    orderDetailDialogVisible.value = false;
    payDialogVisible.value = true;
    timerId = setInterval(getOrderPayStatus, 2000, res.result.orderNo);
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function getOrderPayStatus(orderNo:string) {
  const res = await orderService.getOrderPayStatus(orderNo);
  if (res.success) {
    if(res.result){
      payDialogVisible.value = false;
      clearInterval(timerId);
      await installSuiteMarket(res.result)
    }
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

function closeOrder(){
  clearInterval(timerId);
}

async function installSuiteMarket(bill?: string) {
  let suiteId = Number(paramsData.params.suiteId);
  const res = await suiteMarketService.installSuiteMarket(suiteId,bill);
  if (res.success) {
    ElMessage({ type: 'success', message: '安装成功' });
    await router.push('/suite/list');
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function querySuiteMarketInfo() {
  let suiteId = Number(paramsData.params.suiteId);
  const res = await suiteMarketService.querySuiteMarketDetail(suiteId);
  if (res.success) {
    suiteMarketInfo.value = res.result;
    if(res.result.suiteHelpDocJson.length > 0){
      suiteHelpDocList.value = JSON.parse(res.result.suiteHelpDocJson);
    }
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}
</script>

<template>
  <div class="suite-market-detail">
    <div class="suite-head">
      <img v-if="suiteMarketInfo.suiteImage != ''" :src="suiteMarketInfo.suiteImage" />
      <h3>
        {{ suiteMarketInfo.suiteName }}
        <p>{{ suiteMarketInfo.suiteDesc }}</p>
        <div class="price"><em>{{ suiteMarketInfo.suitePrice }}</em>元</div>
      </h3>
      <div class="operation-button">
        <a v-if="suiteMarketInfo.installStatus" class="btn">已安装</a>
        <a v-else class="btn" @click="handleInstallSuiteMarket">安装</a>
      </div>
    </div>
    <div class="suite-doc">
      <el-tabs model-value="apiList">
        <el-tab-pane label="套件接口" name="apiList">
          <el-tabs tab-position="left">
            <template v-for="api in suiteMarketInfo.apiList" :key="api.apiUrl">
              <el-tab-pane :label="api.apiName">
                <div class="api-info">
                  <div>
                    <div class="title">请求头</div>
                    <el-table :data="api.apiHeaders" border size="large" header-cell-class-name="table-header" >
                      <el-table-column prop="paramName" label="参数名称" width="280"/>
                      <el-table-column prop="required" label="是否必填" >
                        <template #default="scope">
                          {{scope.row.required ? '是' : '否'}}
                        </template>
                      </el-table-column>
                      <el-table-column prop="paramDesc" label="参数描述" />
                    </el-table>
                  </div>
                  <div>
                    <div class="title">入参</div>
                    <el-table :data="api.apiInputParams" border size="large" header-cell-class-name="table-header">
                      <el-table-column prop="paramName" label="参数名称" width="280"/>
                      <el-table-column prop="required" label="是否必填" >
                        <template #default="scope">
                          {{scope.row.required ? '是' : '否'}}
                        </template>
                      </el-table-column>
                      <el-table-column prop="paramDesc" label="参数描述" />
                    </el-table>
                  </div>

                  <div>
                    <div class="title">出参</div>
                    <el-table :data="api.apiOutputParams" border size="large" header-cell-class-name="table-header" >
                      <el-table-column prop="paramName" label="参数名称" width="280"/>
                      <el-table-column prop="paramDesc" label="参数描述" />
                    </el-table>
                  </div>
                </div>
              </el-tab-pane>
            </template>
          </el-tabs>
        </el-tab-pane>
        <el-tab-pane label="帮助文档" name="helpDoc">
          <div class="help-doc">
            <template v-for="doc in suiteHelpDocList" :key="doc.docUrl">
              <a :href="doc.docUrl" target="_blank">{{doc.docName}}</a>
            </template>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

    <el-dialog v-model="orderDetailDialogVisible" :close-on-click-modal="false" title="订单详情" width="500" center>
      <div>
        <el-descriptions
            :column="1"
        >
          <el-descriptions-item label="套件名称"> {{ suiteMarketInfo.suiteName }}</el-descriptions-item>
          <el-descriptions-item label="套件价格"><em class="pay-price">{{ suiteMarketInfo.suitePrice }}</em> 元</el-descriptions-item>
          <el-descriptions-item label="套件描述"> {{ suiteMarketInfo.suiteDesc }}</el-descriptions-item>
          <el-descriptions-item label="用户协议"> <el-checkbox v-model="userAgreementCheck"><a @click="openUserAgreement" class="agreement">用户协议</a></el-checkbox></el-descriptions-item>
        </el-descriptions>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleBuySuiteMarket">
            购买
          </el-button>
        </div>
      </template>
    </el-dialog>

    <UserAgreement ref="userAgreementRef"/>

    <!-- order detail -->
    <el-dialog v-model="payDialogVisible" :close-on-click-modal="false" title="订单详情" width="500" @close="closeOrder">
      <div>
        <el-descriptions
            :column="1"
        >
          <el-descriptions-item label="订单名称"> {{ suiteMarketInfo.suiteName }}套件</el-descriptions-item>
          <el-descriptions-item label="订单价格"><em class="pay-price">{{ suiteMarketInfo.suitePrice }}</em> 元</el-descriptions-item>
          <el-descriptions-item label="下单时间">{{ new Date().toLocaleString() }}</el-descriptions-item>
        </el-descriptions>
        <el-tabs model-value="alipay">
          <el-tab-pane label="支付宝" name="alipay">
            <img :src="payQrCode" alt="">
            <div class="pay-tip">支付宝扫描上方二维码完成支付</div>
          </el-tab-pane>
          <!--        <el-tab-pane label="微信" name="wechat">
                    <div class="pay-tip">支付宝扫描上方二维码完成支付</div>
                  </el-tab-pane>-->
        </el-tabs>
      </div>
    </el-dialog>
  </div>
</template>

<style lang="less" scoped>
.suite-market-detail {
  border-radius: 4px;
  height: 100%;
  margin: 0 auto;
  padding: 0 0 16px 24px;
  background-color: #fff;
}

.suite-head {
  display: flex;
  padding: 24px 0;
  align-items: center;
  border-bottom: 1px solid #dee0e3;

  img {
    width: 96px;
    height: 96px;
    margin-right: 24px;
    border-radius: 6px;
  }

  p {
    color: #1f2329;
    margin-top: 5px;
    font-size: 15px;
  }

  .price{
    margin-top: 5px;
    em{
      color: #e3584d;
      font-size: 22px;
      font-weight: 700;
      margin-right: 3px;
    }
  }

  .operation-button {
    margin-left: auto;
    margin-right: 100px;
    padding-left: 10px;

    .btn {
      border-radius: 4px;
      height: 36px;
      display: inline-block;
      line-height: 36px;
      background: #409eff;
      color: #fff;
      width: 100px;
      text-align: center;
      margin-bottom: 10px;
      cursor: pointer;
    }
  }
}

.suite-doc {
  :deep(.el-tabs__nav-wrap)::after {
    background-color: transparent;
    height: 0;
  }

  .api-info{
    padding-left: 15px;
    padding-right: 15px;
    .title{
      margin: 14px 0 12px 0;
    }
  }

  .help-doc a {
    display: block;
    margin: 5px 0;
    text-decoration: none;
    color: #409eff;
  }
}

.pay-price {
  color: #e3584d;
  margin-right: 2px;
}
.pay-tip {
  font-size: 12px;
}

.agreement{
  color: #409eff;
}
</style>
