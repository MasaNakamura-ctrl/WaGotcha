# RESTAPI仕様書
## ログイン機能
### エンドポイント一覧
| メソッド | パス | 処理 |
| --- | ----------- | ------- |
| POST | /api/login | ログイン認証 |

#### POST /api/login
- 概要：ログイン情報のポスト
- リクエスト(例)
```
{
    "userName": "登録しているユーザーID",
    "password": "登録しているパスワード"
}
```
##### 成功時
- ステータス：200 OK
- レスポンス(例)
```
{
    "message": "Login Successful",
    "user": {
        "userId": "登録しているユーザーID",
        "userName": "登録しているユーザー名"
    }
}
```
- ヘッダーに Set-Cookie: JSESSIONID=... が含まれ、クライアントが以降のリクエストでセッションを維持できるようにする。
##### 不正なエラー時
- ステータス：400 Bad Request
- レスポンス(例)
```
{
    "error": "Bad Request"
}
```
##### 認証エラー時
- ステータス：401 Unauthorized
- レスポンス(例)
```
{
    "error": "Unauthorized"
}
```

#### 補足
- ベースURL：http://localhost:8080
- Content-Type：application/json
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
##### 成功時
- ステータス：200 OK
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
※ツツメキが存在しない場合は空配列を返す→[]
```
##### エラー時
- ステータス：500 Internal Server Error
```
{
    "error": "Internal server error"
}
```

#### POST /api/tsutsumekis
- 概要：新しいツツメキの投稿
- リクエスト(例)
```
{
    "tsutsumeki": "新しいツツメキ"
}
```
##### 成功時
- ステータス：201 Created
- レスポンス(例)
```
{
    "id": 3,
    "tsutsumeki": "新しいツツメキ"
}
```
##### エラー時
POSTにおける400及び409ステータスコードの返却は現時点ではそれぞれ下記の理由にてスコープ外とする。

- 400：フロントエンドにてnullと空文字のバリデーションを行なっているため。

- 409：tsutsumekiカラムでは同一内容の登録も許容しているため。

※今後の要件変更に応じ再検討の可能性あり

#### PUT /api/tsutsumekis/{id}
- 概要：指定したidのツツメキを更新
- リクエスト(例)
```
{
    "tsutsumeki": "ツツメキの更新"
}
```
##### 成功時
- ステータス：200 OK
- レスポンス(例)
```
{
    "id": 1,
    "tsutsumeki": "ツツメキの更新"
}
```
##### エラー時
- ステータス：404 Not Found
```
{
    "error": "Not Found"
}
```

また、POSTと同様の理由で400及び409ステータスコードの返却はスコープ外とする。
#### DELETE /api/tsutsumekis/{id}
- 概要：指定したidのツツメキを削除
- リクエスト：なし(IDはURLで指定)
##### 成功時
- ステータス：204 No Content
##### エラー時
- ステータス：404 Not Found
```
{
    "error": "Not Found"
}
```
また、DELETEにおいてステータスコード500の返却は想定外エラー以外では行わないものとしスコープから除外する。

#### 補足
- ベースURL：http://localhost:8080
- Content-Type：application/json