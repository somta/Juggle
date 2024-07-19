<script lang="ts" setup>

const items = [
  { id: 1, name: 'Card Name 1', description: 'This is the card description 1.', image: '12.png' },
  { id: 2, name: 'Card Name 2', description: 'This is the card description 2.', image: '12.png' },
  { id: 2, name: 'Card Name 2', description: 'This is the card description 2.', image: '12.png' },
  { id: 2, name: 'Card Name 2', description: 'This is the card description 2.', image: '12.png' },
  { id: 2, name: 'Card Name 2', description: 'This is the card description 2.', image: '12.png' },

  // 更多数据...
]

</script>

<template>
  <div class="suite-market">
    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :md="8" v-for="item in items" :key="item.id">
        <el-card @click="goToSuiteMarketDetail" style="padding: 10px;cursor: pointer;">
          <div class="card-header">
            <img class="image" src='data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTI0IiBoZWlnaHQ9IjEyNCIgdmlld0JveD0iMCAwIDEyNCAxMjQiIGZpbGw9Im5vbmUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjxwYXRoIGQ9Ik00OS44Nzg0IDQyLjE0MjdDNDYuNTI5OSAxNS43NjU5IDU2Ljc1ODggNi41MTY0NiA2MC42NTIyIDQuODUzMzVDNjUuNzAyMyAyLjg4MDMyIDY3Ljg4NyA3LjU2Mzc3IDY5LjAyODUgMTAuNjY3MkM3MC4xNyAxMy43NzA2IDgxLjA3ODQgNDAuNjAxMiA5MS40Mzg5IDYwLjc2MDFDMTAxLjc5OSA4MC45MTkxIDg2LjEwMTIgODAuMTY2NSA4My4yNjYyIDc5Ljk5NjNDODAuNDMxMiA3OS44MjYyIDUzLjIyNjggNjguNTE5NiA0OS44Nzg0IDQyLjE0MjdaIiBmaWxsPSIjRjdDNjQ2Ii8+CjxwYXRoIGQ9Ik0zMC42NTkzIDY3LjM4MDdDMTQuNTcxIDQ2LjIxMiAxOC44MDQ3IDMzLjA4NzMgMjEuMzQ1IDI5LjcwMDNDMjQuNzMyIDI1LjQ2NjUgMjguOTY1OCAyOC40MzAyIDMxLjUwNiAzMC41NDdDMzQuMDQ2MyAzMi42NjM5IDU2LjkwODYgNTAuNDQ1NyA3NS45NjA1IDYyLjcyMzZDOTUuMDEyNCA3NS4wMDE1IDgxLjA0MSA4Mi4xOTg5IDc4LjUwMDcgODMuNDY5Qzc1Ljk2MDUgODQuNzM5MiA0Ni43NDc2IDg4LjU0OTUgMzAuNjU5MyA2Ny4zODA3WiIgZmlsbD0iIzNGNzRGNiIvPgo8cGF0aCBkPSJNNjIuMjYxNyA5MS43MzAyQzkwLjAxMDIgNzkuNTA3MyA5NS45ODA0IDg3LjEzOTUgOTYuMzYzNiA5Mi40OTUyQzk2LjExMTIgOTUuMDE2OCA5Ni4zMzMgMTAwLjMyMiA3MS4zNzY2IDEwNC40ODJDNTguMzY0MiAxMDYuNjUgNDkuODY4IDExMS42MjQgNDMuMjAzNiAxMTYuOTUxQzM5LjI2OTggMTIwLjA5NiAzNS4xMjUxIDExNy40NzQgMzcuODk1OCAxMTMuMjY4QzQzLjgzNzEgMTA0LjI1IDUzLjMzMjggOTUuNjYzMiA2Mi4yNjE3IDkxLjczMDJaIiBmaWxsPSIjMjU0RkIzIi8+Cjwvc3ZnPgo=' alt=""/>
            <h3>{{ item.name }}</h3>
          </div>
          <div style="padding: 14px;">
            <div class="bottom clearfix">
              <p class="description">{{ item.description }}</p>
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

  .el-col{
    margin-bottom: 20px;
  }
}


.card-header{
  display: flex;
  margin-bottom: 12px;
  align-items: center;

  .image{
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
