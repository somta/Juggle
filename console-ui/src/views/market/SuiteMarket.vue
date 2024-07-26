<script lang="ts" setup>
import { useRouter } from 'vue-router';
import { suiteMarketService } from '@/service';
import { ref } from 'vue';
const router = useRouter();
const suiteMarketList = ref<Record<string, any>[]>([]);

querySuiteMarketList();

async function querySuiteMarketList() {
  const res = await suiteMarketService.querySuiteMarketList();
  if (res.success) {
    suiteMarketList.value = res.result;
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
    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :md="8" v-for="item in suiteMarketList" :key="item.id">
        <el-card @click="goToSuiteMarketDetail(item.id)" style="cursor: pointer">
          <div class="card-header">
            <img class="image" :src="item.suiteImage" alt="" />
            <h3>{{ item.suiteName }}</h3>
          </div>
          <div style="padding: 14px">
            <div class="bottom clearfix">
              <p class="description">{{ item.suiteDesc }}</p>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="less" scoped>
.suite-market {
  padding: 20px;

  .el-col {
    margin-bottom: 20px;
  }
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

/*
.image {
  width: 100%;
  display: block;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.description {
  font-size: 14px;
  color: #666;
}

.clearfix:before, .clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}*/
</style>
