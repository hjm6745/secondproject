---
name: brief
description: 작업 시작 시 프로젝트 현재 상태, 지난 작업 이후 변경점, 다음 개발 방향을 브리핑한다
disable-model-invocation: true
allowed-tools: Read, Grep, Glob, Bash(git status:*), Bash(git log:*), Bash(git diff:*)
---

# 작업 시작 브리핑

코드는 수정하지 말고, 읽기만 해서 아래 형식으로 브리핑해 줘.

## 참고 자료
- 진행 기록: @docs/PROGRESS.md
- 현재 브랜치와 변경 상태: !`git status --short --branch`
- 최근 커밋 10개: !`git log --oneline -10`
- 마지막 커밋 이후 변경 파일: !`git diff --stat HEAD`
- 최근 커밋 5개의 변경 파일: !`git diff --stat HEAD~5 HEAD`

## 브리핑 형식
1. **현재 상태**: PROGRESS.md 기준으로 완료한 기능과 진행 중인 기능 (3~5줄)
2. **지난번 이후 변경점**: 커밋과 아직 커밋하지 않은 변경을 파일별로 한 줄씩. 코드를 열어 보고 무엇이 바뀌었는지 실제로 확인할 것
3. **주의할 점**: 미완성 코드, TODO, 컴파일·테스트가 깨질 만한 부분
4. **오늘 할 일 제안**: PROGRESS.md의 "다음 할 일"과 실제 코드 상태를 비교해서 우선순위 1~3개. 기록과 코드가 다르면 그 차이도 알려 줄 것

Spring은 처음 배우는 중이니, 새로 등장하는 Spring 개념은 한 줄로 설명해 줘.
