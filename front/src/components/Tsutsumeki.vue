<script setup>
import { tsutsumekis } from './tsutsumeki.js'

</script>
<template>
    <h1>ツツメキ</h1>
    <div class="Tsutsumeki-create">
        <textarea class="Tsutsumeki-text" placeholder="ツツメキしてWaGotchaしてもらおう！">
        </textarea>
        <button>ツツメキ</button>
    </div>
    <div class="Tsutsumeki-poted" v-for="(tsutsumeki, index) in tsutsumekis">
        <p class="Tsutsumeki-words">{{ tsutsumeki.tsutsumeki }}</p>
        <button>WaGotcha</button>
        <button>改む</button>
        <button>消つ</button>
    </div>
</template>