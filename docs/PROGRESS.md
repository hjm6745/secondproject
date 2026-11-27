# 프로젝트 진행 기록

## 목표
스프링부트 웹사이트 기능 구현 (포트폴리오용)
우선 범위: REST API → PostgreSQL → JWT 로그인 → Docker 배포 → AI 서비스 연동

## 현재 상태
| 기능 | 상태 | 메모 |
|---|---|---|
| 프로젝트 세팅 | 완료 | Spring Boot 4.1.0, Java 21, JPA, Mustache, H2, Lombok |
| REST API (CRUD) | 완료 | Article CRUD, 응답 DTO 적용, 수정·삭제 fetch 비동기 처리, 테스트 코드 수정 |
| PostgreSQL 연동 | 예정 | 현재 H2 사용 중 |
| JWT 로그인 | 예정 | 신선영 책 8~9장 참고 |
| Docker 배포 | 예정 | |
| AI 서비스 연동 | 예정 | |

## 다음 할 일
1. H2 → PostgreSQL 교체 (드라이버 추가, application.properties 접속 설정)
2. Spring Security + JWT 로그인 추가
3. Docker로 배포

## 결정 사항
- 2026-11-26 구현 순서: PostgreSQL 교체 → JWT 로그인 → Docker 배포 — 홍팍 책 프로젝트를 기반으로 확장하기 위해

## 작업 기록
### 2026-11-25
- Article 응답을 DTO로 변경, 수정·삭제를 fetch로 전환
- 테스트 코드 수정 완료
### 2026-11-23
- fetch 메서드 추가, 비동기 처리 방식으로 수정
