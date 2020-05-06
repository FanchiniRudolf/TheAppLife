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
        if !strKm.isEmpty{
        let km = Int(strKm)!
        
        let miles = Double(km)/1.6
        tfMiles.text = "\(miles)" //miles.description
        closeKeyboard()
        }else{
            let alert = UIAlertController(title: "Warning", message: "You must capture KM", preferredStyle: .alert)
            let acept = UIAlertAction(title: "Acept", style: .default)
            let cancel = UIAlertAction(title: "Cancel", style: .cancel)
            
            alert.addAction(cancel)
            alert.addAction(acept)
            self.present(alert, animated: true, completion: nil)
            
        }
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        closeKeyboard()
    }

    func closeKeyboard(){
        self.view.endEditing(true)
    }

}

