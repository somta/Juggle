<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { useRoute } from 'vue-router';
import { suiteMarketService } from '@/service';
import { SuiteMarket } from '@/typings';
import { ElMessage } from 'element-plus';

const route = useRoute();
let paramsData = reactive({
  params: route.params,
});
const suiteMarketInfo = ref<SuiteMarket>({
  id: null,
  suiteCode: '',
  suiteName: '',
  suiteImage: '',
  suiteDesc: '',
});

const headerData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
];

querySuiteMarketList();

async function installSuiteMarket() {
  let suiteId = Number(paramsData.params.suiteId);
  const res = await suiteMarketService.installSuiteMarket(suiteId);
  if (res.success) {
    ElMessage({ type: 'success', message: '安装成功' });
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function querySuiteMarketList() {
  let suiteId = Number(paramsData.params.suiteId);
  const res = await suiteMarketService.querySuiteMarketDetail(suiteId);
  if (res.success) {
    suiteMarketInfo.value = res.result;
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
      </h3>
      <div class="operation-button">
        <a class="btn" @click="installSuiteMarket">安装</a>
      </div>
    </div>
    <div class="suite-doc">
      <el-tabs model-value="apiList">
        <el-tab-pane label="套件接口" name="apiList">
          <el-tabs tab-position="left" class="demo-tabs">
            <el-tab-pane label="发生邮件">
              <div class="api-info">
                <div>
                  <div>请求头</div>
                  <el-table :data="headerData" style="width: 100%">
                    <el-table-column prop="date" label="Date" width="180" />
                    <el-table-column prop="name" label="Name" width="180" />
                    <el-table-column prop="address" label="Address" />
                  </el-table>
                </div>
                <div>
                  <div>入参</div>
                  <el-table :data="headerData" style="width: 100%">
                    <el-table-column prop="date" label="Date" width="180" />
                    <el-table-column prop="name" label="Name" width="180" />
                    <el-table-column prop="address" label="Address" />
                  </el-table>
                </div>

                <div>
                  <div>出参</div>
                  <el-table :data="headerData" style="width: 100%">
                    <el-table-column prop="date" label="Date" width="180" />
                    <el-table-column prop="name" label="Name" width="180" />
                    <el-table-column prop="address" label="Address" />
                  </el-table>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="批量发送邮件">批量发送邮件</el-tab-pane>
            <el-tab-pane label="获取授权">获取授权</el-tab-pane>
          </el-tabs>
        </el-tab-pane>
        <el-tab-pane label="帮助文档" name="helpDoc">
          <div class="help-doc">
            <a href="https://baidu.com" target="_blank">如何申请Key和token</a>
            <a href="https://baidu.com" target="_blank">如何api接口说明</a>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<style lang="less" scoped>
.suite-market-detail {
  border-radius: 4px;
  height: 100%;
  margin: 0 auto;
  padding: 0 0 16px 24px;
}

.suite-head {
  display: flex;
  padding: 24px 0px;
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

  .help-doc a {
    display: block;
    margin: 5px 0px;
    text-decoration: none;
    color: #409eff;
  }
}
</style>
