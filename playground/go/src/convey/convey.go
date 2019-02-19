package main

import (
	"fmt"
	"math/rand"
)

type Dot struct {
	X int
	Y int
}

type Cell struct {
	Dot
	Alive bool
}

const BufferSize = 10

var ShiftAround = [8]Dot{{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}}

var SampleWorld = [][]Cell{{Cell{Dot{0, 0}, false}, Cell{Dot{0, 1}, true}, Cell{Dot{0, 2}, false}},
						   {Cell{Dot{1, 0}, false}, Cell{Dot{1, 1}, true}, Cell{Dot{1, 2}, false}},
						   {Cell{Dot{2, 0}, false}, Cell{Dot{2, 1}, true}, Cell{Dot{2, 2}, false}}}

var SampleWorld2= [][]Cell{{Cell{Dot{0, 0}, false}, Cell{Dot{0, 1}, false}, Cell{Dot{0, 2}, false}},
						   {Cell{Dot{1, 0}, false}, Cell{Dot{1, 1}, true}, Cell{Dot{1, 2}, false}},
						   {Cell{Dot{2, 0}, false}, Cell{Dot{2, 1}, true}, Cell{Dot{2, 2}, false}}}

func randomWorld(size int) (world [][]Cell, err error) {
	if size < 3 {
		err = fmt.Errorf("The size of world should be no less than 3!")
		return
	}
	world = [][]Cell{}
	for i := 0; i < size; i++ {
		world = append(world, []Cell{})
		for j := 0; j < size; j++ {
			world[i] = append(world[i], Cell{Dot{i, j}, rand.Intn(10) >= 7 })
		}
	}
	return
}

func refresh(world [][]Cell) bool {
	workSize := len(world) * len(world[0])
	workChan := make(chan struct{}, workSize)
	bornChan := make(chan Dot, BufferSize)
	dieChan := make(chan Dot, BufferSize)
	collectChan := make(chan struct{}, 2)

	// 采集变化
	toBorn := []Dot{}
	toDie := []Dot{}
	go collect(bornChan, &toBorn, collectChan)
	go collect(dieChan, &toDie, collectChan)

	// 计算变化
	for i := 0; i < len(world); i++ {
		for j := 0; j < len(world[i]); j++ {
			go work(i, j, world, workChan, bornChan, dieChan)
		}
	}

	// 轮询
	for i := 0; i < workSize; i++ {
		<-workChan
	}
	close(workChan)
	close(bornChan)
	close(dieChan)
	<-collectChan
	<-collectChan

	if len(toBorn) == 0 && len(toDie) == 0 {
		return false
	}
	// 更新地图
	update(world, toBorn, true)
	update(world, toDie, false)
	return true
}

func collect(src <-chan Dot, target *[]Dot, collectChan chan<- struct{}) {
	for true {
		if d, ok := <-src; !ok {
			collectChan <- struct{}{}
			break
		} else {
			*target = append(*target, d)
		}
	}
}

func work(x int, y int, world [][]Cell, workChan chan<- struct{}, bornChan chan<- Dot, dieChan chan<- Dot) {
	width := len(world)
	height := len(world[0])
	alives := 0
	for _, s := range ShiftAround {
		newX := x + s.X
		newY := y + s.Y
		if newX < 0 || newX >= width || newY < 0 || newY >= height {
			continue
		}
		if world[newX][newY].Alive {
			alives += 1
		}
	}
	if world[x][y].Alive {
		if alives < 2 || alives > 3 {
			dieChan <- Dot{x, y}
		}
	} else if alives == 3 {
		bornChan <- Dot{x, y}
	}
	workChan <- struct{}{}
}

func update(world [][]Cell, dots []Dot, alive bool) {
	for _, d := range dots {
		world[d.X][d.Y].Alive = alive
	}
}

func main() {
	// 初始化地图
	world, err := randomWorld(3)
	if err != nil {
		panic(err)
	}
	fmt.Println(world)
	//world := SampleWorld
	//world := SampleWorld2
	// 刷新直至不再变化
	for refresh(world) {
		fmt.Println(world)
	}
}
