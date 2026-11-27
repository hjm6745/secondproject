---
name: wrap
description: 작업 종료 시 오늘 한 일과 다음 할 일을 docs/PROGRESS.md에 기록한다
disable-model-invocation: true
allowed-tools: Read, Edit, Write, Bash(git status:*), Bash(git log:*), Bash(git diff:*)
---

# 작업 마무리 기록

- 오늘 변경: !`git diff --stat HEAD`
- 오늘 커밋: !`git log --oneline --since=midnight`

위 변경 내용과 이번 대화를 바탕으로 @docs/PROGRESS.md를 갱신해 줘.

1. "현재 상태"의 기능별 상태를 실제 코드에 맞게 고칠 것
2. "작업 기록" 맨 위에 오늘 날짜로 한 일을 3줄 이내로 추가할 것
3. "다음 할 일"을 우선순위 순으로 다시 정리할 것
4. 이번에 내린 설계 결정(라이브러리 선택, 구조 변경 등)이 있으면 "결정 사항"에 이유와 함께 추가할 것

수정한 뒤 변경 내용을 보여 주고, 커밋은 내가 확인한 다음에 할게.
