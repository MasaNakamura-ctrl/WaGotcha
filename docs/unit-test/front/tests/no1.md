# 単体テスト仕様書
## 実施項目
| No   | 画面 | テスト処理 | 前提条件 | 操作手順 | 期待結果 | 実施結果 |
| --- | ----------- | ------- | ------- | ------- | ------- | ------- |
| 1 | 一覧画面 | 初期表示 | tsutsumeki.jsにインポートできるデータが存在する | アプリケーションを立ち上げlocalhost:5173を表示 | 画面設計書で明記したWaGotcha!!の画面が表示されること |OK|

## 画面設計書の画像
![画面設計書の画像](../../../screen-design/img/Tsutsumeki.png)

## tsutsumeki.js
```javascript
import { ref } from 'vue'

export const tsutsumekis = ref([
    {id:1, tsutsumeki: "六段の調べやりたい人いない？"},
    {id:2, tsutsumeki: "来週演奏会やります！"},
    {id:3, tsutsumeki: "○市でもくもく会やってませんか？"}
])
```
## テスト実施
### アプリケーション立ち上げ
```
npm run dev
```
![URL入力](./img/wagotcha-url.png)
### 実施結果
![トップ画面](./img/wagotcha-top.png)