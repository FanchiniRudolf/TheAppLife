//
//  ViewController.swift
//  ConvertKmMiles
//
//  Created by user167967 on 4/29/20.
//  Copyright © 2020 ITESM. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    //References to the text fields
    @IBOutlet weak var tfKilometers: UITextField!
    @IBOutlet weak var tfMiles: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    //Call from button
    @IBAction func convert(_ sender: Any) {
        //get data from fields
        let strKm = tfKilometers.text!
        let km = Int(strKm)!
        
        let miles = Double(km)/1.6
        tfMiles.text = "\(miles)" //miles.description
    }


}

