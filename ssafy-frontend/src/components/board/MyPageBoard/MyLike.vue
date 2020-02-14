<template>
  <div class="post-wrapper">
    <div class="faq-section__qnas">
      <div class="qna animate">
        <div class="qna__q" style="display: flex; justify-content:space-between;">
          <div class="post-list-title" style="margin-right: 15px;"><span style="color:#F67280">{{categoryName}}</span> | {{title}} | {{creator}}</div>
          <div style="font-size: 0.8em; min-width: 87px;">
            <span class="board-date" style="margin-left: 8px; border-left: 1px solid black; padding-left: 8px;">{{date.slice(2, 16)}}</span>
          </div>
        </div>
        <div class="qna__a"></div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'MyLike',
    props: {
      date: {
        type: String
      },
      title: {
        type: String
      },
      creator: {
        type: String
      },
      categoryName: {
        type: String
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