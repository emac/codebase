// Copyright © 2016 Alan A. A. Donovan & Brian W. Kernighan.
// License: https://creativecommons.org/licenses/by-nc-sa/4.0/

// See page 125.

// Findlinks2 does an HTTP GET on each URL, parses the
// result as HTML, and prints the links within it.
//
// Usage:
//	findlinks url ...
package main

import (
	"fmt"
	"net/http"
	"os"

	"golang.org/x/net/html"
	"sort"
)

// visit appends to links each link found in n, and returns the result.
func visit(links map[string]int, n *html.Node) map[string]int {
	if n.Type == html.ElementNode && n.Data == "a" {
		for _, a := range n.Attr {
			if a.Key == "href" {
				links[a.Val] += 1
			}
		}
	}
	for c := n.FirstChild; c != nil; c = c.NextSibling {
		links = visit(links, c)
	}
	return links
}

//!+
func main() {
	for _, url := range os.Args[1:] {
		links, err := findLinks(url)
		if err != nil {
			fmt.Fprintf(os.Stderr, "findlinks2: %v\n", err)
			continue
		}
		sortAndPrint(links)
	}
}

func sortAndPrint(links map[string]int) {
	keys := []string{}
	for k := range links {
		keys = append(keys, k)
	}
	sort.Strings(keys)
	for _, link := range keys {
		fmt.Printf("%v: %v\n", link, links[link])
	}
}

// findLinks performs an HTTP GET request for url, parses the
// response as HTML, and extracts and returns the links.
func findLinks(url string) (map[string]int, error) {
	resp, err := http.Get(url)
	if err != nil {
		return nil, err
	}
	if resp.StatusCode != http.StatusOK {
		resp.Body.Close()
		return nil, fmt.Errorf("getting %s: %s", url, resp.Status)
	}
	doc, err := html.Parse(resp.Body)
	resp.Body.Close()
	if err != nil {
		return nil, fmt.Errorf("parsing %s as HTML: %v", url, err)
	}
	return visit(map[string]int{}, doc), nil
}

//!-
