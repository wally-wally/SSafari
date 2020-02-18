<template>
    <div>
        <div class="portfolio-header" style="padding: 0 2%;">
            <!-- <span class="portfolio-count">{{ portfolioCnt }}개의 스터디모집 게시글</span> -->
            <div style="width: 60%;">
                <v-autocomplete
                    v-model="searchStudyGroupKeyword"
                    :items="groupTitles"
                    :label="'스터디 모임을 쉽게 찾아보세요'"
                    persistent-hint
                    prepend-icon="computer"
                    color="#ffC837"
                    max-width="700px">
                </v-autocomplete>
            </div>
            <span class="portfolio-btn">
                <router-link to="studygroup/create">
                    <v-flex xs12 text-xs-center round my-5>
                    <v-btn color="#f7b157" dark >스터디 모집하기</v-btn>
                    </v-flex>
                </router-link>
            </span>
        </div>
        <v-layout>
            <v-flex>
                <StudyGroupList :studyGroups="studyGroups"></StudyGroupList> <!-- @showPortfolioCount="onPortfolioCount" -->
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
import StudyGroupList from '@/components/studygroup/StudyGroupList'
import axios from 'axios'
export default {
    name : 'StudyGroupindex',
    components: {
		StudyGroupList,
    },
    data() {
        return {
            portfolioCnt: 0,
            isEditing: false,
            searchStudyGroupKeyword: null,
            groupTitles: ['전체 보기'],
            studyGroups: []
        }
    },
    mounted() {
        axios.get('api/portfolios')
            .then(response => {
                this.studyGroups = response.data
                response.data.forEach(data => {
                    this.groupTitles.push(data.title)
                })
            })
    },
    watch: {
        searchStudyGroupKeyword() { 
            axios.get('api/portfolios')
                .then(response => {
                    if (this.searchStudyGroupKeyword === '전체 보기' || this.searchStudyGroupKeyword === undefined) {
                        this.studyGroups = response.data
                    } else {
                        let arr = response.data.filter(res => {
                            let boardTitle = res['title']
                            console.log(boardTitle.includes(this.searchStudyGroupKeyword))
                            return boardTitle.includes(this.searchStudyGroupKeyword)
                        })
                        this.studyGroups = arr
                    }
                })
            }
    }
}
</script>

<style>
    .portfolio-header {
        display: flex;
        justify-content: space-between;
    }

    .portfolio-count {
        font-size: 1.2em;
        font-weight: bold;
        font-family: 'Noto Sans KR', sans-serif;
        line-height: 4;
    }
</style>