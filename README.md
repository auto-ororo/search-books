# SearchBooks

## 説明

キーワードを元にGoogle Books APIを検索し､取得した本情報を一覧表示するアプリケーションです｡

### 動作環境

- Android 5.0 Lollipop 以上

## 技術スタック

### デザインパターン

AAC(Android Architecture Components)､Android Jetpackを用いたMVVMパターンで実装

### 開発言語

Kotlin Ver1.3.71

### SDK

| SDK               | バージョン |
| ----------------- | ---------- |
| minSdkVersion     | 21         |
| compileSdkVersion | 29         |
| targetSdkVersion  | 29         |
| buildToolsVersion | 29.0.3     |

### 採用ライブラリ

| ライブラリ      | バージョン | 概要                                              |
| --------------- | ---------- | ------------------------------------------------- |
| Retrofit2       | 2.7.1      | HTTPクライアント                                  |
| Moshi           | 1.6.0      | JSON整形                                          |
| Coroutine       | 1.3.5      | 非同期処理･軽量スレッド                           |
| Lifecycle       | 2.2.0      | Activity,Fragment,ViewModelのライフサイクルを取得 |
| Navigation      | 2.2.2      | Fragment間の画面遷移                              |
| Material Design | 1.1.0      | マテリアルUIコンポーネント                        |
| Glide           | 4.11.0     | 画像描画ライブラリ                                |
| Timer           | 4.7.1      | ログ出力                                          |

### 採用API

[Google Books API](https://developers.google.com/books?hl=ja)

## 使い方

 検索キーワードを入力して「検索」ボタンをタップすると､キーワードを含む本の一覧が表示されます｡

<img src="https://user-images.githubusercontent.com/23581157/82151417-9cb75b80-9896-11ea-99a6-74e0a5424c33.png" width=40% height=40% >

本をタップすると､書籍名がToast表示されます｡

## 実行方法

1. プロジェクトをAndroid Studioで開く
2. メニュー欄｢Run｣ -> ｢Run 'app'｣をクリックしてアプリを実行する
