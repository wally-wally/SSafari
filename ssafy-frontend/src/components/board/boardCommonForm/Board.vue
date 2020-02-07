<template>
  <div class="post-wrapper">
    <div class="faq-section__qnas">
      <!-- 화면의 너비가 800px 이상일 때 -->
      <div class="qna animate1">
        <div class="qna__q" style="display: flex; justify-content:space-between;">
          <div class="post-list-title" style="margin-right: 15px;">{{title}}</div>
          <div style="font-size: 0.8em; min-width:130px;">
            <span>{{date.slice(2, 16)}} | {{username}}</span>
            <span class="pl-3"><i class="far fa-heart" style="color: crimson;">100</i></span> <!-- 추후 '100'을 {{ like }}로 수정 -->
            <span class="pl-1"><i class="far fa-comments" style="color: skyblue;">100</i></span>
          </div>
        </div>
        <div class="qna__a">{{body}}</div>
      </div>
      <!-- 화면의 너비가 800px 미만일 때-->
      <div class="qna animate2">
        <div class="qna__q" style="display: block;">
          <div class="post-list-title">{{title}}</div>
          <div>
            <span>{{date.slice(2, 16)}} | {{username}}</span>
            <span class="pl-3"><i class="far fa-heart" style="color: crimson;">100</i></span> <!-- 추후 '100'을 {{ like }}로 수정 -->
            <span class="pl-1"><i class="far fa-comments" style="color: skyblue;">100</i></span>
          </div>
        </div>
        <div class="qna__a">{{body}}</div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'Board',
    props: {
      date: {
        type: String
      },
      title: {
        type: String
      },
      body: {
        type: String
      },
      username: {
        type: String
      },
      memberid: {
        type: Number
      },
      like: {
        type: Number
      }
    },
    computed: {
      postYearMonth() {
        let date = new Date(this.date)
        let formatedMonth = date.getMonth() + 1 < 9 ? '0' : ''
        return `${date.getFullYear()}-${formatedMonth}${date.getMonth()+1}`
      },
      postDay() {
        let date = new Date(this.date)
        return date.getDate()
      }
    }
  }
</script>
<style>
  .post-list-title {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
  }

  .post-wrapper {
    margin-bottom: 15px;
    padding: 0 10px;
  }

  .faq-section__qnas .qna {
    background: #fff;
    margin-bottom: 15px;
    border: 1px solid #D3DBE1;
  }

  .faq-section__qnas .qna:last-child {
    margin-bottom: 0;
  }

  .faq-section__qnas .qna__q,
  .faq-section__qnas .qna__a {
    font-family: 'ShBook';
    font-style: normal;
    font-weight: normal;
    font-size: 20px;
    line-height: 30px;
    letter-spacing: 0.03em;
    color: #000000;
  }

  .faq-section__qnas .qna__a {
    letter-spacing: 0;
    font-size: 18px;
    line-height: 26px;
    max-width: calc(100% - 40px);
    border: 0;
  }

  .animate .animate2 {
    background: #D3DBE1;
    border-radius: 6px;
    transition: all .3s ease;
    margin-bottom: 40px;
  }

  .qna.active {
    background: #FFFFFF;
  }

  .qna {
    transition: all .2s ease;
  }

  .qna:not(.active):hover {
    background: #C4CFD7
  }

  .animate,
  .animate>*,
  .animate>*>*,
  .animate>*>*>*,
  .smart-start .subtitle.animate1::before {
    transition: opacity .6s ease, transform .6s ease;
  }

  .faq-section__qnas .animate1 .qna__q
  .faq-section__qnas .animate2 .qna__q {
    font-size: 17px;
    line-height: 24px;
    min-height: 50px;
    height: 70px;
    padding: 24px 20px;
  }

  @media (min-width: 800px) {
    .animate2 {
      display: none;
    }
  }

  @media (max-width: 799px) {
    .animate1 {
      display: none;
    }
    .faq-section__qnas .animate2 .qna__q {
      font-size: 15px;
      padding-top: 13px;
    }
  }
</style>