# 概要

SeleniumのAPIをRhino経由でJavascriptからたたけるようにするツールです。

# 使い方

distフォルダにSeleniumJSのバイナリがありますが、
ライブラリ系は、ライセンスの都合で含めていません
Rhino
http://www.mozilla-japan.org/rhino/download.html
のjs.jarを
Selenium（protobuf-java-2.4.1）
http://www.seleniumhq.org/download/
より、中のjarファイルとlibs内のjarファイルを
libsフォルダにおいてください。

java -jar SeleniumJS.jar [jsファイルパス]

で動作

オプション
-d デバックモード
ブラウザを閉じず、繰り返し同じスクリプトを実行するダイアログを表示します。
スクリプト作成の際のトライアンドエラーに使用


