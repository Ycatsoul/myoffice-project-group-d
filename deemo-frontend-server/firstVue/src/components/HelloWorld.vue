<template>
  <div class="hello">
    <div @click= "toIndex">
      <h1>hahahahha</h1>
    </div> 
<div>
  <h3>我是一个标题</h3>
	<p>我是一个段落</p>
	<p>我是一个段落</p>
	<p>我是一个段落</p>
	<h3>我是一个标题</h3>
	<p>我是一个段落</p>
	<p>我是一个段落</p>
	<p>我是一个段落</p>
	<h3>我是一个标题</h3>
	<p>我是一个段落</p>
	<p>我是一个段落</p>
	<p>我是一个段落</p>
	<h3>我是一个标题</h3>
</div>
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <div v-for="(item,index) in test" :key="index">
       <h3 style="color:blue;font-size:50px;text-align:center;background-color:green">{{item.topicTypeName}}</h3>
       <div v-for="(question,index1) in item.questionList" :key="index1">
         <h4 style="color:green"> {{question.questiontitle}}</h4>
         <div v-for="(question1,index2) in question.questionAnswer" :key="index2">
           <div v-if="item.topicType==='singleChoice'">
             <input type="radio" :id="question1.answer" class="square" :name= "question.questiontitle" value=""/>
             <label :for="question1.answer"></label>
            {{question1.selectItem}}  {{question1.answer}}
          </div> 
         <div v-if="item.topicType=='multiChoice'">
         <input type="checkbox" class="circle" :name= "question.questiontitle" value=""/>
           {{question1.selectItem}}  {{question1.answer}}
          </div> 
         </div>
        </div>
    </div>
  </div>
</template>
<script>
import test from'../../testData.js'
  
export default {
 
  name: 'HelloWorld',
  data () {
    return {
      test:test,
      msg: 'Welcome to Your Vue.js App',
    }
  },
  methods:{ 
       toIndex:function () {
          this.$router.push("/index");
         }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
div{
  position:relative;
  left:20px;
  text-align: left;
}

h3+p{
	color:red;
}
div h3:first-child{
  color:blue;
}
.square {
display: none;
}
.square+label {
position: relative;
height: 20px;
width: 20px;
border-radius: 50%;
border: 1px solid #9a9a9a;
background-color: transparent;
display: inline-block;
top: 5px;
}
 .square:checked+label {
border: 1px solid green;
}
.square:checked+label::after {
position: absolute;
content: "";
width: 14px;
height: 14px;
top: 1px;
left: 1px;
border: 2px solid white;
border-radius: 50%;
background-color: yellowgreen;
} 
</style>
