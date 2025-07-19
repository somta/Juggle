<script lang="ts" setup>
import { useRouter } from 'vue-router';
import { suiteMarketService } from '@/service';
import {ref} from 'vue';
import {Search} from "@element-plus/icons-vue";
import { reactive } from 'vue';
import ClassifyFilter from "@/components/common/ClassifyFilter.vue";
const router = useRouter();
const suiteMarketList = ref<Record<string, any>[]>([]);

const pageNum = ref(1);
const loading = ref(false);
const noMoreSuite = ref(false);
const filterData = ref([{
  title: "套件价格",
  key: "priceStatus",
  options : [{label: "全部",value: null},{label: "免费",value: 0},{label: "付费",value: 1}]
}]);
const filterValue = reactive({
  suiteName: '',
  suiteClassifyId: null,
  priceStatus: null,
});

querySuiteMarketClassifyList();
querySuiteMarketList();

async function changeSuiteName(){
  noMoreSuite.value = false;
  suiteMarketList.value = [];
  await querySuiteMarketList();
}


async function changeFilter(val:any){
  filterValue.suiteClassifyId = val.suiteClassify;
  filterValue.priceStatus = val.priceStatus;
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
    suiteClassifyId: filterValue.suiteClassifyId,
    priceStatus: filterValue.priceStatus,
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
    suiteClassifyId: filterValue.suiteClassifyId,
    priceStatus: filterValue.priceStatus,
  });
  if (res.success) {
    suiteMarketList.value = res.result;
  }
}

async function querySuiteMarketClassifyList() {
  const suiteClassifyFilter = await suiteMarketService.querySuiteMarketClassifyList();
  if(suiteClassifyFilter != null){
    filterData.value.push(suiteClassifyFilter);
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
      <el-input v-model="filterValue.suiteName"
          class="suite-filter-name"
          size="large"
          placeholder="请输入你想查找的套件名称"
          :suffix-icon="Search"
          @change="changeSuiteName"
      />
      <ClassifyFilter :data="filterData" @change="changeFilter"/>
    </div>
    <el-row v-if="suiteMarketList.length" :gutter="20" v-infinite-scroll="loadNextSuiteMarket" infinite-scroll-delay="500" infinite-scroll-immediate="false">
      <el-col :xs="24" :sm="12" :md="8" v-for="item in suiteMarketList" :key="item.id">
        <el-card class="card" @click="goToSuiteMarketDetail(item.id)">
          <div class="card-header">
            <img class="image" :src="item.suiteImage" @error="e => { e.target.src = '/suite/default.svg' }" alt="" />
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
    <el-empty v-else/>
    <div v-if="loading" class="loading">数据加载中...</div>
  </div>
</template>

<style lang="less" scoped>
.suite-market {
  padding: 20px;

  .suite-filter {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
    flex-direction: column;
    align-items: center;
    .suite-filter-name {
      width: 500px;
    }
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
