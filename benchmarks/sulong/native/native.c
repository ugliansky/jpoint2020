#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void goNative() {
}


int fib(int k) {
  int prev = 0;
  int curr = 1;
  for (int i = 0; i < k; i++) {
      int summ = prev + curr;
      prev = curr;
      curr = summ;
  }
  return curr;
}

int nativeWithSomeWork(int num) { 
    return fib(num);
}
