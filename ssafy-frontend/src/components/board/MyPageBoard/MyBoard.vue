<template>
  <div class="post-wrapper">
    <div class="faq-section__qnas">
      <div class="qna animate">
        <div class="qna__q" style="display: flex; justify-content:space-between;">
          <div class="post-list-title" style="margin-right: 15px;"><span style="color:#F67280">{{categoryName}}</span> | {{title}}</div>
          <div style="font-size: 0.8em; min-width: 87px;">
            <span class="board-date" style="margin-left: 8px; border-left: 1px solid black; padding-left: 8px;">{{date.slice(2, 16)}}</span>
          </div>
        </div>
        <div class="qna__a">{{body}}</div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'MyPost',
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

<style>
.color-666 {
  color: #666;
}

.color-333 {
  color: #333;
}

.h-100 {
  height: 100%;
}

.post-list-title {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}

@media (max-width: 800px) {
  .post-list-title {
    font-size: 15px;
  }

  .board-date {
    font-size: 12px;
  }
}

.post-wrapper {
  margin-bottom: 15px;
}
.qna__a a {
  color: #173BEC;
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

.faq-section__qnas .qna__q svg {
  width: 20px;
  height: 20px;
  right: 20px;
}

.faq-section__qnas .qna__q {
  height: 75px;
  padding: 0 20px;
}

.faq-section__qnas .qna.active {
  background: #fff;
}

.faq-section__qnas .qna.active .qna__a {
  border: 0;
  padding: 24px 0;

}

.faq-section__qnas .qna svg path {
  transition: all .3s ease;
}

.faq-section__qnas .qna__q span {
  width: calc(100% - 40px);
}

.faq-section__qnas .qna.active svg path {
  fill: #0045F3;
}

.qna {
  background: #D3DBE1;
  border-radius: 6px;
  transition: all .3s ease;
  margin-bottom: 40px;
}

.qna.active {
  background: #FFFFFF;
}

.qna.active svg {
  transform: rotate(45deg);
}

.qna.active .qna__a {
  height: auto;
  padding: 20px 0 40px;
  border-top: 1px solid #D3DBE1;
}

.qna__q {
  font-family: 'ShBook';
  display: flex;
  align-items: center;
  font-size: 32px;
  line-height: 43px;
  color: #000000;
  padding-right: 100px;
  position: relative;
  padding: 0 40px;
  height: 134px;
  cursor: pointer;
}

.qna__q span {
  width: calc(100% - 100px);
}

.qna__q svg {
  position: absolute;
  right: 40px;
  top: calc(50% - 15px);
  transition: all .3s ease;
}

.qna__a {
  border-top: 1px solid transparent;
  height: 0;
  padding: 0;
  overflow: hidden;
  font-family: 'ShBook';
  font-size: 18px;
  line-height: 26px;
  color: #000000;
  max-width: calc(100% - 80px);
  margin: 0 auto;
  transition: all .3s ease;
}

.qna {
  transition: all .2s ease;
}

.qna:not(.active):hover {
  background: #C4CFD7
}

.qna.animate:nth-child(1) {
  transition-delay: .1s;
}

.qna.animate:nth-child(2) {
  transition-delay: .2s;
}

.qna.animate:nth-child(3) {
  transition-delay: .3s;
}

.qna.animate:nth-child(4) {
  transition-delay: .4s;
}

.qna.animate:nth-child(5) {
  transition-delay: .5s;
}

.qna.animate:nth-child(6) {
  transition-delay: .6s;
}

.qna.animate:nth-child(7) {
  transition-delay: .7s;
}

.qna.animate:nth-child(8) {
  transition-delay: .8s;
}

.qna.animate:nth-child(9) {
  transition-delay: .9s;
}

.animate,
.animate>*,
.animate>*>*,
.animate>*>*>*,
.smart-start .subtitle.animate::before {
  transition: opacity .6s ease, transform .6s ease;
}

.faq-section__qnas .qna__q {
  font-size: 17px;
  line-height: 24px;
  min-height: 50px;
  height: 70px;
  padding: 24px 20px;
}

.faq-section__qnas .qna__a {
  font-size: 16px;
  line-height: 26px;
}

.faq-section__qnas .qna__q svg {
  height: 14px;
  width: 14px;
  top: calc(50% - 7px);
}

.faq-section__qnas .qna__q span {
  width: calc(100% - 30px);
}

.faq-section {
  padding-bottom: 100px;
}
</style>