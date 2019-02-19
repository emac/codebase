package main

import "fmt"
import (
	"encoding/json"
	"bytes"
)

type StructA struct {
	A int `json:"a,omitempty"`
}

type StructB struct {
	StructA
	B int `json:"b"`
}

func createB1() StructB {
	return StructB{StructA{1}, 2}
}

func createB2() *StructB {
	return &StructB{StructA{}, 2}
}

func hello() {
	i := 1
	i ++
	fmt.Printf("hello, %v!\n", i)
}

func increaseA(nums *[1]int) {
	for i := range nums {
		nums[i] = nums[i] + 1
	}
}

func increaseS(nums []int) {
	for i := range nums {
		nums[i] = nums[i] + 1
	}
}

func increaseM(pairs map[int]int) {
	for k, v := range pairs {
		pairs[k] = v + 1
	}
}

func main() {
	fmt.Printf("hello, world\n")
	hello()
	hello()
	fmt.Println("//////////////// 指针")
	x := 1
	p := &x // p, of type *int, points to x
	fmt.Println(p)
	fmt.Println(*p) // "1"
	*p = 2          // equivalent to x = 2
	fmt.Println(x)  // "2"
	fmt.Println("//////////////// 元组赋值")
	p1, p2 := 1, 2
	p1, p2 = p2, p1
	fmt.Println("//////////////// array & slice")
	array1 := [...]int{1}
	fmt.Println(array1)
	increaseA(&array1)
	fmt.Println(array1)
	slice1 := make([]int, 1)
	fmt.Println(slice1)
	increaseS(slice1)
	fmt.Println(slice1)
	fmt.Println("//////////////// map")
	map1 := map[int]int{}
	map1[0] = 1
	fmt.Println(map1)
	increaseM(map1)
	fmt.Println(map1)
	fmt.Println("//////////////// struct")
	b := createB1()
	// b := &createB1() // 调用函数返回的是值，并不是一个可取地址的变量。
	b.A += 1
	fmt.Println(b)
	b = *createB2()
	fmt.Println(b)
	fmt.Println("//////////////// json")
	bm, _ := json.MarshalIndent(b, "", "\t")
	fmt.Printf("%s\n", bm)
	var b2 StructB
	json.Unmarshal(bm, &b2)
	fmt.Println(b2)
	json.NewDecoder(bytes.NewReader(bm)).Decode(&b2)
	fmt.Println(b2)
}
