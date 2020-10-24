<template>
  <div id="app">
    <el-container>
      <el-container>
        <el-aside width="200px">
          <el-menu default-active="$route.path"
                   class="el-menu-vertical-demo"
                   background-color="#364152"
                   text-color="#fff">
            <el-menu-item index="/dish" @click="toDish">
              <i class="el-icon-user"></i>
              <span slot="title">菜品管理</span>
            </el-menu-item>
            <el-menu-item index="/canteen" @click="toCanteen">
              <i class="el-icon-s-custom"></i>
              <span slot="title">食堂管理</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {

    }
  },
  methods: {
    toDish() {
      if(this.$route.path !== '/dish') {
        this.$router.push('/dish')
      }
    },
    toCanteen() {
      if(this.$route.path !== '/canteen') {
        this.$router.push('/canteen')
      }
    },
  }
}
</script>

<style>

html, body, #app, .el-container {
  margin: 0px;
  padding: 0px;
  height: 100%;
}

#map {
  width: 100%;
  height: 600px;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: left;
  line-height: 80px;
  font-size: 32px;
  font-weight: bold;
}

.el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 30px;
}

.el-aside {
  background-color: #334154;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-menu{
  border-right-width: 0;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  /* text-align: center; */
  /* line-height: 160px; */
}

</style>
