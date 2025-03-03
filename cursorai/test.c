#include <stdio.h>
#include <string.h>
#include <windows.h>

// 이솝 우화 구조체 정의
typedef struct {
    wchar_t title[100];
    wchar_t moral[200];
    wchar_t story[500];
} Fable;

// 이솝 우화를 출력하는 함수
void printFable(const Fable* fable) {
    wprintf(L"\n=== %ls ===\n", fable->title);
    wprintf(L"이야기:\n%ls\n\n", fable->story);
    wprintf(L"교훈:\n%ls\n", fable->moral);
    wprintf(L"================\n\n");
}

int main() {
    // 콘솔 출력 인코딩을 UTF-8로 변경
    system("chcp 65001");
    
    printf("\n이솝 우화 모음\n");
    printf("\n=== 토끼와 거북이 ===\n");
    printf("이야기:\n");
    printf("자만심이 강한 토끼가 거북이와 경주를 하게 되었습니다.\n");
    printf("토끼는 거북이를 얕보고 중간에 낮잠을 잤지만,\n");
    printf("끝까지 포기하지 않고 걸어간 거북이가 결국 결승점에 먼저 도착했습니다.\n\n");
    printf("교훈:\n");
    printf("꾸준함이 성공의 열쇠이다.\n");
    printf("================\n\n");

    printf("=== 개미와 베짱이 ===\n");
    printf("이야기:\n");
    printf("여름 내내 베짱이는 노래만 부르며 놀았고, 개미는 열심히 일하며 겨울을 준비했습니다.\n");
    printf("추운 겨울이 되자 베짱이는 먹을 것이 없어 고생했지만,\n");
    printf("개미는 따뜻하고 배부르게 겨울을 보냈습니다.\n\n");
    printf("교훈:\n");
    printf("미래를 준비하는 자세가 중요하다.\n");
    printf("================\n");

    return 0;
}