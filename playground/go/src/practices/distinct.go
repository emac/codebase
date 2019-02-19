package main

import "fmt"

func Distinct(ss []string) (int, error) {
	if len(ss) == 0 {
		return -1, fmt.Errorf("slice must not be empty")
	}

	pos := 1
	last := ss[0]
	for i := 1; i < len(ss); i++ {
		cur := ss[i]
		if cur == last {
			continue
		}
		last = cur
		ss[pos] = cur
		pos++
	}
	return pos, nil
}

func main() {
	//ss := []string{}
	ss := []string{"a", "b", "b", "c", "b"}
	pos, error := Distinct(ss)
	if error == nil {
		for i := 0; i < pos; i++ {
			fmt.Println(ss[i])
		}
	} else {
		panic(error)
	}
}
