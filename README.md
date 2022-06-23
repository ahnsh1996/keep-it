# Keep it!
## 프로젝트 소개
웹서핑을 하다가, 혹은 다른 무언가를 하다가 우리는 그 내용을 저장하고 싶을 때가 있습니다.  
이를 위해 텍스트를 복사하고 메모 앱을 켜서 붙여넣기하고 저장하는 과정은 간단해 보이지만 생각보다 귀찮은 작업입니다.  
그러다가 귀찮아서 저장을 하지 않고 넘어가 결국 잊어버리기도 합니다.

그래서 Keep it이 탄생했습니다.  
Keep it은 현재 하고 있는 작업을 최대한 방해하지 않으면서 쉽게 기록할 수 있는 메모 앱니다.  
현재 보고 있는 것을 저장하기 위해서 그저 텍스트를 선택하고 저장하면 끝!

또한 Keep it은 저장된 메모에서 URL, 전화번호, 이메일을 따로 모아서 보여주는 기능도 가지고 있습니다.
앞으로도 Keep it은 사용자의 편의성을 위해서 기능은 개선해나가는 것을 목표로 하고 있습니다.

## 예시
다음은 Keep it에 저장할 예시 문장입니다.  
```
Keep it은 현재 하고 있는 작업을 최대한 방해하지 않으면서 쉽게 기록할 수 있는 메모 앱니다.  
현재 보고 있는 것을 저장하기 위해서 그저 텍스트를 선택하고 저장하면 끝!

URL 예시 : https://github.com/, https://github.com/ahnsh1996/keep-it
이메일 예시 : ahnsh1996@gmail.com, ahnsh1996@naver.com, ahnsh1996@daum.net, ahnsh1996@nate.com, ahnsh1996@test.com
전화번호 예시 : 010-1234-5678, 01012345679, 01056781234, 010-1234-1234

※ 이메일과 전화번호 예시는 대부분 임의의 테스트용 메일 및 번호입니다.
```

|웹서핑 중 빠른 저장|Keep it 앱|
|-|-|
|<img width="400" alt="keep it 메모 저장" src="https://user-images.githubusercontent.com/77680436/175304574-c4e5c244-7304-47d7-9950-7979e67a0a11.gif">|<img width="400" alt="keep it 메모 확인" src="https://user-images.githubusercontent.com/77680436/175304564-0debb557-1b7b-4863-b137-f4f7aca3ed4e.gif">|


## 기술 스택	
<table>
  <tr>
    <td><b>Language</b></td>
    <td>Kotlin based</td>
  </tr>
  <tr>
    <td><b>Architecture</b></td>
    <td>MVVM</td>
  </tr>
  <tr>
    <td><b>Jetpack Components</b></td>
    <td>DataBinding, LiveData, ViewModel, Navigation, Room</td>
  </tr>
  <tr>
    <td><b>Concurrency</b></td>
    <td>Coroutines</td>
  </tr>
  <tr>
    <td><b>Other Library</b></td>
    <td>Glide, Jsoup, Gson</td>
  </tr>
</table>

## 기타
### 제약 사항
Keep it은 선택 가능한 텍스트에 대해서 동작합니다.
일부 앱의 텍스트들은 선택이 불가능하도록 설정되어 있으며, 이 경우 Keep it을 통한 빠른 저장이 불가능합니다.

또한 Marshmallow(6.0, API Level 23) 이하의 환경이나 일부 앱에서는 동작하지 않을 수 있습니다.
