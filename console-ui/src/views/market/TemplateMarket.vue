<script lang="ts" setup>
import { useRouter } from 'vue-router';
import {templateMarketService} from '@/service';
import {ref} from 'vue';
import {Search} from "@element-plus/icons-vue";
import { reactive } from 'vue';
import ClassifyFilter from "@/components/common/ClassifyFilter.vue";
const router = useRouter();
const templateMarketList = ref<Record<string, any>[]>([]);

const pageNum = ref(1);
const loading = ref(false);
const noMoreTemplate = ref(false);
const filterData = ref([{
  title: "模板价格",
  key: "priceStatus",
  options : [{label: "全部",value: null},{label: "免费",value: 0},{label: "付费",value: 1}]
}]);
const filterValue = reactive({
  templateName: '',
  templateClassifyId: null,
  priceStatus: null,
});


queryTemplateMarketClassifyList();
queryTemplateMarketList();

async function changeTemplateName(){
  noMoreTemplate.value = false;
  templateMarketList.value = [];
  await queryTemplateMarketList();
}

async function changeFilter(val:any){
  filterValue.templateClassifyId = val.templateClassify;
  filterValue.priceStatus = val.priceStatus;
  noMoreTemplate.value = false;
  templateMarketList.value = [];
  await queryTemplateMarketList();
}

async function loadNextTemplateMarket(){
  if(noMoreTemplate.value){
    return;
  }
  loading.value = true;
  pageNum.value = pageNum.value+1;
  const res = await templateMarketService.queryTemplateMarketList({
    pageNum:pageNum.value,
    pageSize:15,
    templateName:filterValue.templateName,
    templateClassifyId: filterValue.templateClassifyId,
    priceStatus: filterValue.priceStatus,
  });
  if (res.success) {
    loading.value = false;
    if(res.result.length > 0){
      res.result.forEach(item => {
        templateMarketList.value.push(item);
      });
    } else {
      noMoreTemplate.value = true;
    }
  }
}

async function queryTemplateMarketList() {
  pageNum.value = 1;
  const res = await templateMarketService.queryTemplateMarketList({
    pageNum:pageNum.value,
    pageSize:15,
    templateName:filterValue.templateName,
    templateClassifyId: filterValue.templateClassifyId,
    priceStatus: filterValue.priceStatus,
  });
  if (res.success) {
    templateMarketList.value = res.result;
  }
}

async function queryTemplateMarketClassifyList() {
  const templateClassifyFilter = await templateMarketService.queryTemplateMarketClassifyList();
  if(templateClassifyFilter != null){
    filterData.value.push(templateClassifyFilter);
  }
}

function goToTemplateMarketDetail(templateId: number) {
  router.push({
    name: 'template-market-detail',
    params: {
      templateId: templateId,
    },
  });
}

</script>

<template>
  <div class="template-market">
    <div class="template-filter">
      <el-input v-model="filterValue.templateName"
          class="template-filter-name"
          size="large"
          placeholder="请输入你想查找的模板信息"
          :suffix-icon="Search"
          @change="changeTemplateName"
      />
      <ClassifyFilter :data="filterData" @change="changeFilter"/>
    </div>
    <el-row v-if="templateMarketList.length" :gutter="20" v-infinite-scroll="loadNextTemplateMarket" infinite-scroll-delay="500" infinite-scroll-immediate="false">
      <el-col :xs="24" :sm="12" :md="8" v-for="item in templateMarketList" :key="item.id">
        <el-card class="card" @click="goToTemplateMarketDetail(item.id)">
          <div class="card-header">
            <template v-for="(suite, index)  in item.suiteList">
              <img class="image" :src="suite.suiteImage" @error="e => { e.target.src = '/suite/default.svg' }" alt="" />
              <template v-if="index !== item.suiteList.length - 1">
                <img src="../../assets/link.svg" alt="">
              </template>
            </template>
          </div>
          <div class="card-body">
            <h3>{{ item.templateName }}</h3>
            <div class="bottom clearfix">
              <p class="description">{{ item.templateRemark }}</p>
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
.template-market {
  background-color: var(--el-bg-color-overlay);
  padding: 20px;

  .template-filter {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
    flex-direction: column;
    align-items: center;
    .template-filter-name {
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
    width: 30px;
    height: 30px;
    border-radius: 12px;
  }
}

.card-body{
  margin-top: 10px;
  margin-bottom: 10px;

  h3{
    margin-bottom: 6px;
  }
}

.loading{
  text-align: center;
  font-size: 18px;
}
</style>
