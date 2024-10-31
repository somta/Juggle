<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { useRoute,useRouter } from 'vue-router';
import {orderService, templateMarketService} from '@/service';
import {CreateOrder, TemplateMarketInfo} from '@/typings';
import { ElMessage } from 'element-plus';
import QRCode from 'qrcode'
import UserAgreement from '../common/UserAgreement.vue'

const route = useRoute();
const router = useRouter();
let paramsData = reactive({
  params: route.params,
});
const templateMarketInfo = ref<TemplateMarketInfo>({
  id: null,
  templateName: '',
  templateRemark: '',
  recommend: false,
  priceStatus: 0,
  templatePrice: 0,
  templateContent:'',
  suiteList: [],
  noBuySuiteList: [],
});
const orderDetailDialogVisible = ref(false);
const userAgreementCheck = ref(false);
const userAgreementRef = ref();
const payDialogVisible = ref(false);
const payQrCode = ref();
const createOrder = ref<CreateOrder>({
  orderNo: '',
  qrCode:''
});

queryTemplateMarketInfo();

async function handleUseTemplateMarket() {
  if(templateMarketInfo.value.priceStatus == 1 && templateMarketInfo.value.templatePrice > 0){
    orderDetailDialogVisible.value = true;
  } else {
    await userTemplateMarket();
  }
}

function openUserAgreement(){
  if (userAgreementRef.value) {
    userAgreementRef.value.openUserAgreementDialog();
  }
}

async function handleBuyTemplateMarket() {
  if(!userAgreementCheck.value){
    ElMessage({ type: 'error', message: '请先勾选用户协议' });
    return;
  }
  await createTemplateOrder();
}

let timerId;
async function createTemplateOrder() {
  let templateId = Number(paramsData.params.templateId);
  const res = await orderService.createOrder({
    orderName: templateMarketInfo.value.templateName + "模板",
    orderType: 2,
    goodsId: templateId,
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
      await userTemplateMarket(res.result)
    }
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

function closeOrder(){
  clearInterval(timerId);
}

async function userTemplateMarket(bill?: string) {
  let templateId = Number(paramsData.params.templateId);
  const res = await templateMarketService.useTemplateMarket(templateId,bill);
  if (res.success) {
    ElMessage({ type: 'success', message: '安装成功' });
    await router.push('/flow/define');
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function queryTemplateMarketInfo() {
  let templateId = Number(paramsData.params.templateId);
  const res = await templateMarketService.queryTemplateMarketDetail(templateId);
  if (res.success) {
    templateMarketInfo.value = res.result;
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}
</script>

<template>
  <div class="template-market-detail">
    <div class="template-head">
      <div>
        <div class="suite-list">
          <template v-for="(suite, index)  in templateMarketInfo.suiteList">
            <img class="image" :src="suite.suiteImage" @error="e => { e.target.src = '/suite/default.svg' }" alt="" />
            <template v-if="index !== templateMarketInfo.suiteList.length - 1">
              <img src="../../assets/link.svg" alt="">
            </template>
          </template>
        </div>
        <h3>{{ templateMarketInfo.templateName }}</h3>
        <p>{{ templateMarketInfo.templateRemark }}</p>
        <div class="price"><em>{{ templateMarketInfo.templatePrice }}</em>元</div>
      </div>
      <div class="operation-button">
        <a class="btn" @click="handleUseTemplateMarket">立即使用</a>
      </div>
    </div>
    <div class="template-content">

    </div>

    <div class="template-recommend-list">
        <h3>相关流程模板</h3>
    </div>

    <el-dialog v-model="orderDetailDialogVisible" :close-on-click-modal="false" title="订单详情" width="500" center>
      <div>
        <el-descriptions
            :column="1"
        >
          <el-descriptions-item label="模板名称"> {{ templateMarketInfo.templateName }}</el-descriptions-item>
          <el-descriptions-item label="模板价格"><em class="pay-price">{{ templateMarketInfo.templatePrice }}</em> 元</el-descriptions-item>
          <el-descriptions-item label="模板描述"> {{ templateMarketInfo.templateRemark }}</el-descriptions-item>
          <el-descriptions-item label="用户协议"> <el-checkbox v-model="userAgreementCheck"><a @click="openUserAgreement" class="agreement">用户协议</a></el-checkbox></el-descriptions-item>
        </el-descriptions>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="handleBuyTemplateMarket">
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
          <el-descriptions-item label="订单名称"> {{ templateMarketInfo.templateName }}模板</el-descriptions-item>
          <el-descriptions-item label="订单价格"><em class="pay-price">{{ templateMarketInfo.templatePrice }}</em> 元</el-descriptions-item>
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
.template-market-detail {
  border-radius: 4px;
  height: 100%;
  margin: 0 auto;
  padding: 0 0 16px 24px;
  background-color: #fff;
}

suite-list img{
  width: 32px;
  height: 32px;
  margin-right: 24px;
  border-radius: 6px;
}

.template-head {
  display: flex;
  padding: 24px 0;
  align-items: center;
  border-bottom: 1px solid #dee0e3;

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
