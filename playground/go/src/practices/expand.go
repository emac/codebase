package main

import (
	"fmt"
	"strings"
)

func Upper(s string) string {
	return strings.ToUpper(s)
}

func Expand(s string, f func(string) string) string {
	if !strings.Contains(s, "foo") {
		return s
	}
	newFoo := f("foo")
	return strings.Replace(s, "foo", newFoo, -1)
}

func main() {
	fmt.Println(Expand("abc", Upper))
	fmt.Println(Expand("abcfooabc", Upper))
}
