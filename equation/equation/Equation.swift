//
//  Equation.swift
//  equation
//
//  Created by user167967 on 5/6/20.
//  Copyright © 2020 ITESM. All rights reserved.
//

import Foundation


class equation{
    private var a: Int
    private var b: Int
    private var c: Int
    
    init(_ a: Int, _ b: Int, _ c: Int){
        self.a = a
        self.b = b
        self.c = c
    }
    
    //Root
    func calcRoot1() -> String {
        let discriminate = Double( b*b - 4*a*c)
        if discriminate>=0 {
            let root = (-1*b + Int(sqrt(discriminate)))/(2*a)
            let strRoot = String(format: "%.2f", root)
            return strRoot
        }
        return "Complex"
    }
    
    func calcRoot2() -> String {
        let discriminate = Double( b*b - 4*a*c)
        if discriminate>=0 {
            let root = (-1*b - Int(sqrt(discriminate)))/(2*a)
            let strRoot = String(format: "%.2f", root)
            return strRoot
        }
        return "Complex"
    }
    
}
