<template>
<v-container>
  <v-select
    label="CodeType"
    :items="items"
    item-text="text"
    item-value="val"
    v-model="type"
  ></v-select>
  <v-label> 제목 : 
  <v-text-field v-model="title"></v-text-field>
  </v-label>
  <div style="height:50px" id="codetype"></div>
  <codemirror ref="myCm"
              :value="code"
              :options="cmOptions"
              @ready="onCmReady"
              @focus="onCmFocus"
              @input="onCmCodeChange">
  </codemirror>
  <button @click="codecreate">작성하기</button>
</v-container>

</template>


<script>
import CodeMirror from 'codemirror'
import { codemirror } from 'vue-codemirror'
import axios from 'axios'
import 'codemirror/lib/codemirror.css'
import 'codemirror/mode/xml/xml.js'
import 'codemirror/mode/javascript/javascript.js'
import 'codemirror/theme/base16-dark.css'
import 'codemirror/mode/python/python.js'
export default {
  components: {
    codemirror
  },
  data () {
    return {
      title : '',
      items : [
        { text : 'Python', val : 'text/x-python'},
        {text : 'Java', val : 'text/x-java'},
        { text : 'JavaScript', val : 'text/javascript'},
        { text : 'Html', val : 'text/html'},
        { text :'C++' , val : 'text/x-c++src'} ],
      type : '',
      code: '',
      cmOptions: {
        tabSize: 4,
        mode: 'text/x-python',
        // mode: '',
        theme: 'base16-dark',
        lineNumbers: true,
        line: true,
      }
    }
  },
  methods: {
    onCmReady(cm) {
      cm.options.mode = this.type
      console.log('the editor is readied!', cm)
    },
    onCmFocus(cm) {
      cm.options.mode = this.type
      console.log(cm.options.mode)
    },
    onCmCodeChange(newCode) {
      console.log('this is new code', newCode)
      this.code = newCode
    },
    codecreate() {
      const formData = new FormData()
      const data = {
        'title' : this.title,
        'body' : this.code,
        'type' : this.type
      }
      console.log(data)
    },
    getcodes() {
      axios.get('api/codes')
      .then(response => {
        console.log(response.data)
      })
    }
  },
  mounted(){
    this.getcodes()
  },
  computed: {
    codemirror() {
      return this.$refs.myCm.codemirror
    }
  },
  watch: {
    type: function(type) {
      const val= document.querySelector('#codetype')
      val.innerHTML = type
      this.cmOptions.mode = type
    },
  }
}
</script>

<style>

</style>