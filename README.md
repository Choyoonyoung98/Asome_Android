# A-CHAT

**한이음 2019 IBM 대표이사상**

개발 기간 : 2018년 03월 02일  ~ 2018년 11월 30일


## [ About ]

**AI 기반의 기업형 Realtime Chatbot 개발을 통한 업무 일정 관리 스마트 시스템**입니다.

**1. ChatBot 시스템** <br>
팀 일정과 개인 일정에 대하여 ChatBot에서 통합 관리가 가능합니다.

**2. 프로젝트 관리 시스템** <br>
프로젝트 정보와 프로젝트에서 팀원이 맡은 업무 내용과 기간 등의 Data를 Server에 대응시켜 저장한다. 또한 이를 ChatBot으로 관리 가능합니다.

**3. 팀 일정 관리 시스템** <br>
추가된 프로젝트 일정을 담은 Calendar를 자동 생성하고 팀 일정과 팀원들의 업무 진행 상태를 사용자에게 보여줍니다.

**4. 개인 일정 관리 시스템** <br>
Google Calendar와 연동하여 개인 일정을 ChatBot으로 관리하고 주기적인 알림을 주는 Reminder 기능을 수행합니다.

**5. AI 스피커** <br>
기존 ChatBot에 AI Speaker를 붙여 질의응답의 STT, TTS가 가능합니다.

## [ Architecture ]             

![workflow](https://github.com/Choyoonyoung98/Haniem2018/blob/master/Asome_Architecture.png)

## [ App Image ]             

![appImage](https://github.com/Choyoonyoung98/Haniem2018/blob/master/appImg.png)


## [ Develop Environment ]

### S/W 
- OS: Linux Ubuntu, **Android**
- Language :  **Java**, PHP, Pyhon, SQL   
- IDE : **Android Studio**, Eclipse  
- Server: NginX
- DB: RDBMS, NoSQL
- FrameWork: Netty

### H/W
- HardWare: Raspberry Pi 3.0, USB 마이크, USB 스피커, IBM TJ Bot Case


## [ API ]

- [Firebase - Google 연동 로그인](https://github.com/firebase/quickstart-android/blob/d854bf66ff07abe08f371a07d261508df7df49fb/auth/README.md)
- [Firebase - Cloud Messaging](https://github.com/firebase/quickstart-android/blob/d854bf66ff07abe08f371a07d261508df7df49fb/messaging/README.md)
- [Google Assistant](https://developers.google.com/assistant/sdk/reference/rpc)
- [Google Calendar](https://developers.google.com/calendar)
- [Google Dialog Flow](https://cloud.google.com/dialogflow/docs/quick/api?hl=ko)

