<template>
<v-container >
    <v-row>
        <v-col cols="3">
            제목 : {{code.title}}
        </v-col>
        <v-spacer />
        <v-col >
            {{code.created_at}}
        </v-col>
    </v-row>
    <v-row>
        <v-col>
        내용 : {{code.body}}
        </v-col>
    </v-row>
    <v-row>
        <v-col>
        {{code.memberid}}
        {{this.langchange[code.lang]}}
        </v-col>
    </v-row>
    <codemirror
        :options="showOptions"
        :value="code.code"
  ></codemirror>
</v-container>
</template>

<script>
import axios from 'axios'
import CodeMirror from 'codemirror'
import { codemirror } from 'vue-codemirror'
import 'codemirror/lib/codemirror.css'
import 'codemirror/mode/xml/xml.js'
import 'codemirror/mode/javascript/javascript.js'
import 'codemirror/theme/base16-dark.css'
import 'codemirror/mode/python/python.js'

export default {
    name : 'CodeDetail',
    components: codemirror,
    props :{
      id : {type:Number}  
    },
    data() {
        return {
            langchange:  {
                'text/x-python' : 'Python',
                'text/x-java' : 'Java',
                'text/javascript' : 'JavaScript',
                'text/html' : 'Html',
                'text/x-c++src' : 'C++'
            },
            code : {},
            showOptions : {
                    tabsize : 4,
                    mode : 'text/x-python',
                    lineNumbers: true,
                    readOnly : true,
                    line :true,
                    lineSeparator : '↵'
                },
        }
    },
    methods : {
        getcode() {
            axios.get(`api/code/${this.id}`)
            .then(response => {
            console.log(response.data)
            this.code = response.data
            })
        }
    },
    mounted() {
        this.getcode()
    }
}
</script>

<style>

</style>