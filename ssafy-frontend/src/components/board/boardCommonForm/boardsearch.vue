<template>
  <v-container>
    <div align="right">
        <form @submit.prevent="nextsearch">
        <v-text-field placeholder="검색어가 없을 시 전체 게시판이 표시됩니다" color="#ffc837" style="display:inline-block;width:50%" v-model="nextq" />
        <i @click="nextsearch" class="searchicon fas fa-search"></i>
        </form>
    </div>
    <div class="row mb-3" align="center">
        <div class="col-3"> 게시판 이름 </div>
        <div class="col-9"> 설명 </div>
    </div>
    <v-divider/>
    <div style="height: 60px;" v-for="board in boards" :key="board.id">
        <div class="boarddetail">
            <router-link class="row" :to="`/board/${board.id}`"> 
            <div class="col-3" align="center" >{{ board.name }}</div>
            <v-spacer/>
            <div class="col-8 text-truncate">{{board.explanation}}</div>
            </router-link>
        </div>
    </div>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
    name : 'searchboard',
    props : {
        q : {type : String}
    },
    data() {
        return {
            boards :[],
            nextq : '',
        }
    },
    mounted() {
        this.searchBoard()
    },
    methods : {
        searchBoard() {
            if (this.q){ 
            axios.get(`api/boardcategory/search/${this.q}`)
                .then(response => {
                    this.boards = response.data
                })
            } else {
                axios.get(`api/boardcategory`)
                .then(response =>
                    this.boards = response.data)
            }
        },
        nextsearch() {
            if (this.nextq){ 
            axios.get(`api/boardcategory/search/${this.nextq}`)
                .then(response => {
                    this.boards = response.data
                    this.nextq = ''
                })
            } else {
                axios.get(`api/boardcategory`)
                .then(response =>{
                    this.boards = response.data
                    this.nextq = ''
                }
                )}
        },
    }
}
</script>

<style>
    .boarddetail:hover {
        border-radius: 10px;
    box-shadow: 1px 1px 3px darkslategrey;
    background-color : #f5f5ff;
    }
    .boarddetail div{
        color : rgba(0,0,0,.87) ;
    }
    .searchicon:hover {
        cursor: pointer;
    }
</style>