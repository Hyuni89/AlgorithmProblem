#include <pthread.h>
#include <cstdio>
#include <unistd.h>
#include <cstring>

using namespace std;

void* doSomething(void *position) {
	int *change = (int*)position;

	printf("before change to 10 [%p]\n", change);
	(*change) = 10;
	printf("after change to 10 [%p][%d]\n", change, *change);
	sleep(5);
	printf("before change to 100 [%p]\n", change);
	(*change) = 100;
	printf("after change to 100 [%p][%d]\n", change, *change);
}

int main() {

	pthread_t t;
	int *pos = new int;
	printf("pos address[%p][%d]\n", pos, *pos);

	pthread_create(&t, NULL, &doSomething, pos); 

	sleep(2);

	delete pos;

	const char *const arr = new char[4];
	for(int i = 0; i < 4; i++) {
		printf("[%p][%x]\n", &arr[i], arr[i]);
	}

	pthread_join(t, NULL);

	for(int i = 0; i < 4; i++) {
		printf("[%p][%x]\n", &arr[i], arr[i]);
	}

	delete[] arr;


	char *ptr = new char;
	printf("[%p][%c]\n", ptr, *ptr);
	printf("[%p][%c]\n", ptr + 100, *(ptr + 100));
	delete ptr;

	return 0;
}
