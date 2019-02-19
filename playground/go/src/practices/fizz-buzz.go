package main

import (
	"fmt"
	"strconv"
	"strings"
)

func FizzBuzz(num int) string {
	if num%15 == 0 {
		return "FizzBuzz"
	} else if num%5 == 0 {
		return "Buzz"
	} else if num%3 == 0 {
		return "Fizz"
	}

	ss := []string{}
	ss = append(ss, strconv.Itoa(num%10))

	for num >= 10 {
		num /= 10
		ss = append(ss, strconv.Itoa(num%10))
	}
	// 反转字符
	lasti := len(ss) - 1
	for i := 0; i < len(ss)/2; i++ {
		ss[i], ss[lasti-i] = ss[lasti-i], ss[i]
	}
	return strings.Join(ss, "")
}

func main() {
	fmt.Println(FizzBuzz(9))
	fmt.Println(FizzBuzz(25))
	fmt.Println(FizzBuzz(30))
	fmt.Println(FizzBuzz(1234))
}
