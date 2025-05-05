# RESTAPI仕様書
## ツツメキのCRUD
### エンドポイント一覧
| メソッド | パス | 処理 |
| --- | ----------- | ------- |
| GET | /api/tsutsumekis | ツツメキ一覧の取得 |
| POST | /api/tsutsumekis | 新規ツツメキの作成 |
| PUT | /api/tsutsumekis/{id} | ツツメキの更新 |
| DELETE | /api/tsutsumekis/{id} | ツツメキの削除 |

※ツツメキ：Xでいうポストされたもののこと

#### GET /api/tsutsumekis
- 概要：ツツメキ一覧の取得
- レスポンス(例)
```
[
    {
        "id":1,
        "tsutsumeki": "読み込むツツメキ"
    },
    {
        "id":2,
        "tsutsumeki": "読み込むツツメキ"
    }
]
```

#### POST /api/tsutsumekis
- 概要：新しいツツメキの投稿
- リクエスト(例)
```
{
    "tsutsumeki": "新しいツツメキ"
}
```
- レスポンス(例)
```
{
    "id": 3,
    "tsutsumeki": "新しいツツメキ"
}
```

#### PUT /api/tsutsumekis/{id}
- 概要：指定したidのツツメキを更新
- リクエスト(例)
```
{
    "tsutsumeki": "ツツメキの更新"
}
```
- レスポンス(例)
```
{
    "id": 1,
    "tsutsumeki": "ツツメキの更新"
}
```

#### DELETE /api/tsutsumekis/{id}
- 概要：指定したidのツツメキを削除
- リクエスト：なし(IDはURLで指定)
- レスポンス(例)
```
{}
```
#### 補足
- ベースURL：http://localhost:8080
- Content-Type：application/json