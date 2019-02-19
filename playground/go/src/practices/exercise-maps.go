package main

import (
	"strings"
	"fmt"
)

func WordCount(s string) map[string]int {
	words := strings.Split(s, " ")
	wc := make(map[string]int)
	for _, w := range words {
		wc[w] = wc[w] + 1
	}

	return wc
}

func main() {
	fmt.Println(WordCount("a b ab a"))
}
