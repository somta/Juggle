<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { useRoute } from 'vue-router';
import { suiteMarketService } from '@/service';
import {SuiteMarketInfo} from '@/typings';
import { ElMessage } from 'element-plus';

const route = useRoute();
let paramsData = reactive({
  params: route.params,
});
const suiteMarketInfo = ref<SuiteMarketInfo>({
  id: null,
  suiteCode: '',
  suiteName: '',
  suiteImage: '',
  suiteDesc: '',
  apiList: []
});

querySuiteMarketInfo();

async function installSuiteMarket() {
  let suiteId = Number(paramsData.params.suiteId);
  const res = await suiteMarketService.installSuiteMarket(suiteId);
  if (res.success) {
    ElMessage({ type: 'success', message: '安装成功' });
  } else {
    ElMessage({ type: 'error', message: res.errorMsg });
  }
}

async function querySuiteMarketInfo() {
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
          <el-tabs tab-position="left">
            <template v-for="api in suiteMarketInfo.apiList" :key="api.apiUrl">
              <el-tab-pane :label="api.apiName">
                <div class="api-info">
                  <div>
                    <div class="title">请求头</div>
                    <el-table :data="api.apiHeaders" border size="large" :header-cell-style="{ background: '#f0f0f0' }" >
                      <el-table-column prop="paramKey" label="参数编码" width="280" />
                      <el-table-column prop="paramName" label="参数名称" />
                      <el-table-column prop="required" label="是否必填" >
                        <template #default="scope">
                          {{scope.row.required ? '是' : '否'}}
                        </template>
                      </el-table-column>
                    </el-table>
                  </div>
                  <div>
                    <div class="title">入参</div>
                    <el-table :data="api.apiInputParameter" border size="large" :header-cell-style="{ background: '#f0f0f0' }" >
                      <el-table-column prop="paramKey" label="参数编码" width="280" />
                      <el-table-column prop="paramName" label="参数名称" />
                      <el-table-column prop="required" label="是否必填" >
                        <template #default="scope">
                          {{scope.row.required ? '是' : '否'}}
                        </template>
                      </el-table-column>
                    </el-table>
                  </div>

                  <div>
                    <div class="title">出参</div>
                    <el-table :data="api.apiOutputParameter" border size="large" :header-cell-style="{ background: '#f0f0f0' }" >
                      <el-table-column prop="paramKey" label="参数编码" width="280" />
                      <el-table-column prop="paramName" label="参数名称" />
                    </el-table>
                  </div>
                </div>
              </el-tab-pane>
            </template>
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

  .api-info{
    padding-left: 15px;
    padding-right: 15px;
    .title{
      margin: 14px 0 12px 0;
    }
  }

  .help-doc a {
    display: block;
    margin: 5px 0px;
    text-decoration: none;
    color: #409eff;
  }
}
</style>
