<script lang="ts" setup>
import { useRouter } from 'vue-router';
import { suiteMarketService } from '@/service';
import { ref } from 'vue';
import {Search} from "@element-plus/icons-vue";
import { reactive } from 'vue';
const router = useRouter();
const suiteMarketList = ref<Record<string, any>[]>([]);

const filterValue = reactive({
  suiteName: '',
  suiteClassifyId: '',
});

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

const options = [
  {
    value: 'Option1',
    label: 'Option1',
  },
  {
    value: 'Option2',
    label: 'Option2',
  },
  {
    value: 'Option3',
    label: 'Option3',
  },
  {
    value: 'Option4',
    label: 'Option4',
  },
  {
    value: 'Option5',
    label: 'Option5',
  },
]

</script>

<template>
  <div class="suite-market">
    <div class="suite-filter">
      <el-select
          v-model="filterValue.suiteClassifyId"
          size="large"
          style="width: 240px;margin-right: 20px;"
      >
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
      <el-input v-model="filterValue.suiteName"
          style="width: 300px"
          size="large"
          :suffix-icon="Search"
      />
    </div>
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

  .suite-filter {
    margin-bottom: 10px;
  }

  .el-col {
    margin-bottom: 20px;
  }
  .description {
    font-size: 13px;
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
</style>
