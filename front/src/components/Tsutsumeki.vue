<script setup>
import { tsutsumekis } from './tsutsumeki.js'

// データ削除
function deleteTsutsumeki(index){
    tsutsumekis.value.splice(index,1)
}

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
        <button @click="deleteTsutsumeki(index)">消つ</button>
    </div>
</template>