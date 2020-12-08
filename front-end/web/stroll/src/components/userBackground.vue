<template lang="pug">
  .user-background
    img.background(:src="imgSrc")

    div(v-if="activeMode==='snow'")
      .snow(v-for="item in 200")

    img.sun(v-if="activeMode==='sun'" :src="sunSrc")

    div(v-if="activeMode==='rain'")
      hr(v-for="item in 100" :style="'left:'+ (Math.floor(Math.random() * 100) + 10) + '%;'+ 'animationDuration:'+ (0.7 + Math.random() * 0.3) + 's;' + 'animationDelay:' + Math.random() * 5 + 's;'")
      hr.thunder

    .audio_animate(v-if="!pause" @click="closeAudio")
      img.horn(:src="horn")
      img.left(:src="left")
      img.right(:src="right")
    .audio_animate.opacity(v-else @click="openAudio")
      img.horn(:src="horn")
      img.pause(:src="x")

    audio(ref="audio" :src="audioSrc")
</template>

<script>
    export default {
      name: "userBackground",
      props: {
        imgSrc: {             // 背景图片
          type: String,
          // default: "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=289489236,794389348&fm=26&gp=0.jpg"
          default: "../../static/bg/4.jpg"
        },
        audioSrc: {           // bgm
          type: String,
          default: '../../static/audio.mp3'
        },
        activeMode:{          // none默认模式 sun光照模式 snow下雪模式
          type: String,
          default:'rain'
        }
      },
      data () {
        return {
          sunSrc:require('../../static/light3.png'),
          horn:require('../../static/horn.png'),
          left:require('../../static/left.png'),
          right:require('../../static/right.png'),
          x:require('../../static/x.png'),
          pause:true,
        }
      },
      created() {

      },
      methods:{
        openAudio(){
          this.pause = false
          this.$refs.audio.play()
        },
        closeAudio(){
          this.pause = true
          this.$refs.audio.pause()
        },
      }
    }
</script>

<style lang="scss" scoped>
  .user-background{
    width: 100%;
    height:100vh;
    overflow: hidden;
    position:fixed;
    top:0;
    left:0;
  }
  .background{
    position: absolute;
    width: 100%;
    height:100vh;
    vertical-align:middle;
    object-fit: cover;
    overflow: hidden;
  }

  @function random_range($min, $max) {
    $rand: random();
    $random_range: $min + floor($rand * (($max - $min) + 1));
    @return $random_range;
  }

  .snow {
    $total: 200;
    position: absolute;
    width: 10px;
    height: 10px;
    background: white;
    border-radius: 50%;

    @for $i from 1 through $total {
      $random-x: random(1000000) * 0.0001vw;
      $random-offset: random_range(-100000, 100000) * 0.0001vw;
      $random-x-end: $random-x + $random-offset;
      $random-x-end-yoyo: $random-x + ($random-offset / 2);
      $random-yoyo-time: random_range(30000, 80000) / 100000;
      $random-yoyo-y: $random-yoyo-time * 100vh;
      $random-scale: random(10000) * 0.0001;
      $fall-duration: random_range(10, 30) * 1s;
      $fall-delay: random(30) * -1s;

      &:nth-child(#{$i}) {
        opacity: random(10000) * 0.0001;
        transform: translate($random-x, -10px) scale($random-scale);
        animation: fall-#{$i} $fall-duration $fall-delay linear infinite;
      }

      @keyframes fall-#{$i} {
        #{percentage($random-yoyo-time)} {
          transform: translate($random-x-end, $random-yoyo-y) scale($random-scale);
        }

        to {
          transform: translate($random-x-end-yoyo, 100vh) scale($random-scale);
        }
      }
    }
  }
  .sun{
    width:auto;
    height:100vh;
    position: absolute;
    top:0;
    left:-100px;
    opacity: 0;
    animation: changes 4s 0.2s linear 1 forwards;
  }
  @keyframes changes {
    0% {
      opacity: 0;
    }
    100%{
      opacity: 1;
    }
  }
  .audio_animate{
    width:40px;
    height:35px;
    position:absolute;
    left:50px;
    bottom:50px;
    display: block;
    opacity: 1;
    transition: opacity 0.5s;
    cursor:pointer;
    z-index:999;
  }
  .audio_animate.opacity{
    opacity: 0.5;
  }
  .horn{
    height:35px;
  }
  .left{
    height:35px;
    animation: leftshake 0.5s linear infinite;
  }
  @keyframes leftshake {
    0% {
      transform: translateX(0);
    }
    50%{
      transform: translateX(1px);
    }
    100%{
      transform: translateX(0);
    }
  }
  .right{
    height:35px;
    animation: rightshake 0.5s linear infinite;
  }
  @keyframes rightshake {
    0% {
      transform: translateX(0);
    }
    50%{
      transform: translateX(-1px);
    }
    100%{
      transform: translateX(0);
    }
  }
  .pause{
    position: absolute;
    left:20px;
    top:12.5px;
    width:10px;
    animation: pauseshake 0.5s ease-out 1 forwards;
  }
  @keyframes pauseshake {
    0% {
      left:20px;
    }
    100%{
      left:15px;
    }
  }

  hr.thunder {
    border: unset;
    position: absolute;
    width: 100vw;
    height: 100vh;
    animation-name: thunder;
    animation-duration: 20s;
    animation-timing-function: linear;
    animation-delay: 5s;
    animation-iteration-count: infinite;
  }

  hr:not(.thunder) {
    width: 15px;
    border-color: rgba(255, 255, 255, 1);
    border-right-color: rgba(255, 255, 255, 1);
    border-right-width: 15px;
    position: absolute;
    bottom: 100%;
    transform-origin: 100% 50%;
    animation-name: rain;
    animation-duration: 1s;
    animation-timing-function: linear;
    animation-iteration-count: infinite;
  }

  @keyframes rain {
    from {
      transform: rotate(105deg) translateX(0);
    }
    to {
      transform: rotate(105deg) translateX(calc(100vh + 20px));
    }
  }

  @keyframes thunder {
    0% {
      background-color: transparent;
    }
    1% {
      background-color: white;
    }
    2% {
      background-color: rgba(255, 255, 255, 0.8);
    }
    8% {
      background-color: transparent;
    }
  }
</style>
