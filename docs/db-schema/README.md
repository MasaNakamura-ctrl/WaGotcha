# DB設計書
## DBのセットアップ
ローカル開発ではDocker内でDBを構築しデータのやり取りをしていました。

テーブルとカラム定義は次章**DB項目定義**を参照してください。

### ディレクトリ
任意のディレクトリから下記の構造で各ファイルを配置していました。

```
任意のディレクトリ
└── wagotcha-db/
    ├── init/
    │   └── init.sql
    └── docker-compose.yml
```
### 各ファイルの記述
- docker-compose.yml
```
version: "3.8"
services:
  postgres:
    image: postgres:latest
    container_name: wagotcha-db
    restart: always # コンテナが落ちた際に自動で再起動
    environment:
      POSTGRES_USER: user # ユーザー名
      POSTGRES_PASSWORD: password # パスワード
      POSTGRES_DB: wagotcha-db # データベース名
    ports:
      - "5432:5432" # ホストの5432番ポートをコンテナの5432番ポートへマッピング
    volumes:
      - pg_data:/var/lib/postgresql/data # 永続化のためのボリュームマウント
      - ./init:/docker-entrypoint-initdb.d
    networks:
      - wagotcha-network
volumes:
  pg_data: # 永続化ボリュームの定義
networks:
  wagotcha-network: # 事前にdocker network create wagotcha-networkでネットワークを作成
    external: true
```
- init/init.sql
```
CREATE TABLE IF NOT EXISTS tsutsumekis(
    id SERIAL PRIMARY KEY,
    userId INTEGER REFERENCES users(userId) ON DELETE CASCADE,
    tsutsumeki VARCHAR(140) NOT NULL,
    tsutsumekiDate TIMESTAMP NOT NULL,
    WaGotchaNum INTEGER NOT NULL
);

INSERT INTO tsutsumekis(userId, tsutsumeki, tsutsumekiDate, WaGotchaNum) VALUES
(1, '六段の調べやりたい人いない？', '2025-05-25 00:00:00', 0),
(1, '来週演奏会やります！', '2025-05-25 09:00:00', 4),
(1, '○市でもくもく会やってませんか？', '2025-05-25 12:00:00', 10)
;

CREATE TABLE IF NOT EXISTS users(
    userId SERIAL PRIMARY KEY,
    userName VARCHAR(20) NOT null,
    password VARCHAR(20) NOT null
);

INSERT INTO users(userName, password) VALUES
('Guest', 'GuestPassword'),
('User1', 'Password1')
;
```

### 起動方法
使用しているOSにてDockerをインストールの上、ターミナルでディレクトリをpostgresへ移動してください。

そうしたら下記のコマンドを入力してください。

```
docker network create wagotcha-network
```
このコマンドを入力するとDBのコンテナと本アプリのコンテナをそれぞれ起動した際に、同じネットワーク上で接続できるようになります。

続けて下記のコマンドを順に実行するとコンテナ内にpostgreSQLのデータベースとテーブルを構築し立ち上げることができます。
```
docker-compose build
docker-compose up -d
```
この内"docker-compose build"は新たに作り直す必要がなければ初回以降の実行は不要です。

テーブルの中身をUIで確認する場合は"docker-compose up -d"でデータベースとテーブルを起動しDBeaverなどで接続するようにしてください。

(接続設定についてはDBをPostgreSQLと指定し、"docker-compose.yml"にあるユーザー名、パスワード、データベース名から入力しホスト名はlocalhostとしてください。)

DBの使用を終えた際にはターミナルで下記のコマンドを実行するようにしてください。
```
docker-compose down
```

## DB項目定義
### テーブル定義
| テーブル名   | 概要 |
| --- | ----------- |
| users | 和楽知屋に登録しているユーザーの情報 |
| tsutsumekis | ツツメキでの投稿内容 |

### カラム定義
#### users
| カラム名 | 型 | 最大文字数 | 主キー | 必須 | 概要 |
| --- | ----------- | ----------- | ----------- | ----------- | ----------- |
| userId | INTEGER |  | ○ | ○ | ユーザー毎に割り当てられるID |
| userName | VARCHAR | 20 |  | ○ | ユーザーのアカウント名 |
| password | VARCHAR | 1024 |  | ○ | ログインに必要となるパスワード |

#### tsutsumekis
| カラム名 | 型 | 最大文字数 | 主キー | 必須 | 概要 |
| --- | ----------- | ----------- | ----------- | ----------- | ----------- |
| id | INTEGER |  | ○ | ○ | ツツメキ毎に割り当てられるID |
| userId | INTEGER |  |  | ○ | ユーザー毎に割り当てられるIDでusersテーブルを参照 |
| tsutsumeki | VARCHAR | 140 |  | ○ | 各ツツメキの投稿内容 |
| tsutsumekiDate | TIMESTAMP |  |  | ○ | ツツメキの投稿日時 |
| WaGotchaNum | INTEGER |  |  | ○ | いいねにあたるボタンを押下された回数 |

※tsutsumekisテーブルのカラム追加はログイン実装後に実施予定