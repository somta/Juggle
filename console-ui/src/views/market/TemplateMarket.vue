<script lang="ts" setup>
import { useRouter } from 'vue-router';
import { suiteMarketService } from '@/service';
import {ref} from 'vue';
import {Search} from "@element-plus/icons-vue";
import { reactive } from 'vue';
const router = useRouter();
const suiteMarketList = ref<Record<string, any>[]>([]);
const suiteMarketClassifyList = ref<Record<string, any>[]>([]);

const pageNum = ref(1);
const loading = ref(false);
const noMoreSuite = ref(false);
const filterValue = reactive({
  suiteName: '',
  suiteClassifyId: null,
});


querySuiteMarketClassifyList();
querySuiteMarketList();

async function changeSuiteClassify(){
  noMoreSuite.value = false;
  suiteMarketList.value = [];
  await querySuiteMarketList();
}

async function changeSuiteName(){
  noMoreSuite.value = false;
  suiteMarketList.value = [];
  await querySuiteMarketList();
}

async function loadNextSuiteMarket(){
  if(noMoreSuite.value){
    return;
  }
  loading.value = true;
  pageNum.value = pageNum.value+1;
  const res = await suiteMarketService.querySuiteMarketList({
    pageNum:pageNum.value,
    pageSize:15,
    suiteName:filterValue.suiteName,
    suiteClassifyId: filterValue.suiteClassifyId
  });
  if (res.success) {
    loading.value = false;
    if(res.result.length > 0){
      res.result.forEach(item => {
        suiteMarketList.value.push(item);
      });
    } else {
      noMoreSuite.value = true;
    }
  }
}

async function querySuiteMarketList() {
  pageNum.value = 1;
  const res = await suiteMarketService.querySuiteMarketList({
    pageNum:pageNum.value,
    pageSize:15,
    suiteName:filterValue.suiteName,
    suiteClassifyId: filterValue.suiteClassifyId
  });
  if (res.success) {
    suiteMarketList.value = res.result;
  }
}

async function querySuiteMarketClassifyList() {
  const res = await suiteMarketService.querySuiteMarketClassifyList();
  if (res.success) {
    suiteMarketClassifyList.value = res.result;
  }
}

function goToSuiteMarketDetail(suiteId: number) {
  router.push({
    name: 'suite-market-detail',
    params: {
      suiteId: suiteId,
    },
  });
}

</script>

<template>
  <div class="suite-market">
    <div class="suite-filter">
      <el-select
          v-model="filterValue.suiteClassifyId"
          size="large"
          style="width: 240px;margin-right: 20px;"
          @change="changeSuiteClassify"
      >
        <el-option label="所有类型" value=""/>
        <el-option
            v-for="classify in suiteMarketClassifyList"
            :key="classify.id"
            :label="classify.classifyName"
            :value="classify.id"
        />
      </el-select>
      <el-input v-model="filterValue.suiteName"
          style="width: 300px"
          size="large"
          :suffix-icon="Search"
          @change="changeSuiteName"
      />
    </div>
    <el-row :gutter="20" v-infinite-scroll="loadNextSuiteMarket" infinite-scroll-delay="500" infinite-scroll-immediate="false">
      <el-col :xs="24" :sm="12" :md="8" v-for="item in suiteMarketList" :key="item.id">
        <el-card class="card" @click="goToSuiteMarketDetail(item.id)">
          <div class="card-header">
            <img class="image" :src="item.suiteImage" alt="" />
            <h3>{{ item.suiteName }}</h3>
          </div>
          <div class="card-body">
            <div class="bottom clearfix">
              <p class="description">{{ item.suiteDesc }}</p>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <div v-if="loading" class="loading">数据加载中...</div>
  </div>
</template>

<style lang="less" scoped>
.suite-market {
  padding: 20px;

  .suite-filter {
    margin-bottom: 20px;
  }

  .el-col {
    margin-bottom: 20px;
  }
  .description {
    font-size: 13px;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
}

.card{
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
}

.card:hover {
  transform: translateY(-10px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.12);
}

.card-header {
  display: flex;
  margin-bottom: 12px;
  align-items: center;

  .image {
    width: 48px;
    height: 48px;
    border-radius: 12px;
    margin-right: 12px;
  }
}

.card-body{
  margin-top: 10px;
  margin-bottom: 10px;
}

.loading{
  text-align: center;
  font-size: 18px;
}
</style>
