<template>
<v-container >
  <v-row align="center">
    <v-col cols="3">
      <v-select
        label="CodeType"
        :items="items"
        item-text="text"
        item-value="val"
        v-model="type"
      ></v-select>
    </v-col>
    <v-spacer />
    <v-col cols="1">
      <label col-1> 제목 : 
      </label>
    </v-col>
    <v-col cols="6">
      <v-text-field col-8 v-model="title"></v-text-field>
    </v-col>
  </v-row>
  <textarea v-model="body" id="" cols="30" rows="10"></textarea>
  <codemirror ref="myCm"
              :value="code"
              :options="cmOptions"
              @ready="onCmReady"
              @focus="onCmFocus"
              @input="onCmCodeChange">
  </codemirror>
  <v-checkbox v-model="anonymousStatus" label="익명" value="익명" class="annoyCheck"/>

  <hr>
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
      body : '',
      anonymousStatus : false,
      codes : [],
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
        'body' : this.body,
        'code' : this.code,
        'lang' : this.type,
        'anonymous' : this.anonymousStatus,
        'memberid' : this.$store.state.memberid,
      }
      // axios.get('/api/code',data)
      // .then(response =>{
      //   console.log(response)
      // }).catch(error => {
      //   console.log(error)
      // })
    },
  },
  computed: {
    codemirror() {
      return this.$refs.myCm.codemirror
    }
  },
  watch: {
    type: function(type) {
      this.cmOptions.mode = type
    },
  }
}
</script>

<style>

</style>