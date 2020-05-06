//
//  ViewController.swift
//  equation
//
//  Created by user167967 on 5/6/20.
//  Copyright © 2020 ITESM. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    //Coeficients
    @IBOutlet weak var tfA: UITextField!
    @IBOutlet weak var tfB: UITextField!
    @IBOutlet weak var tfC: UITextField!
    
    
    //Roots
    @IBOutlet weak var tfRoot1: UITextField!
    @IBOutlet weak var tfRoot2: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


    @IBAction func solveEcuation(_ sender: Any) {
        if let a = Int(tfA.text!),
        let b = Int(tfB.text!),
        let c = Int(tfC.text!){
        
        let model = equation(a, b, c)
        
        let root1 = model.calcRoot1()
        let root2 = model.calcRoot2()
        
        tfRoot1.text = root1
        tfRoot2.text = root2
        }else{
            print("Error, capture data")
        }
        
        
    }
}

