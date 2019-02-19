package main

import "fmt"

func RecursiveAdd(num int) int {
	if num < 10 {
		return num
	}
	sum := Sum(num)
	for sum >= 10 {
		sum = Sum(sum)
	}
	return sum
}

func Sum(num int) int {
	if num < 10 {
		return num
	}
	sum := num%10
	for num >= 10 {
		num /= 10
		sum += num%10
	}
	return sum
}

func main() {
	fmt.Println(RecursiveAdd(1231))
	fmt.Println(RecursiveAdd(12311231))
	fmt.Println(RecursiveAdd(123112311231))
	fmt.Println(RecursiveAdd(1231123112311231))
}
