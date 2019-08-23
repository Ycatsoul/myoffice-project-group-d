<template>
  <div class="head">
    <h1>{{this.$router.currentRoute.name}}</h1>
    <h2>Essential Links</h2>
  </div>
</template>
<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
     "test":test,
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.head{
position: absolute;
left:500px;
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
