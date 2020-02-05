<template>
    <div>
        <div class="portfolio-header" style="padding: 0 2%;">
            <span class="portfolio-count">{{ portfolioCnt }}개의 스터디모집 게시글</span>
            <span class="portfolio-btn">
                <router-link to="portfolio/create">
                    <v-flex xs12 text-xs-center round my-5>
                    <v-btn color="#f7b157" dark >스터디 모집하기</v-btn>
                    </v-flex>
                </router-link>
            </span>
        </div>
        <v-layout>
            <v-flex xs12>
                <PortfolioList @showPortfolioCount="onPortfolioCount" :limits="6" :load-more="true"></PortfolioList>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
import PortfolioList from '@/components/portfolio/PortfolioList'
export default {
    name : 'Portfolioindex',
    components: {
		PortfolioList,
    },
    data() {
        return {
            portfolioCnt: 0
        }
    },
    methods: {
        onPortfolioCount(value) {
            this.portfolioCnt = value
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