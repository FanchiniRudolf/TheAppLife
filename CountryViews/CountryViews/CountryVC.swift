//
//  CountryVC.swift
//  CountryViews
//
//  Created by user167967 on 5/13/20.
//  Copyright © 2020 ITESM. All rights reserved.
//

import UIKit

class Country_VC: UIViewController {
    
    
    @IBOutlet weak var lblCountry: UILabel!
    
    
    var countryName: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        if countryName != ""{
            lblCountry.text = countryName
        }
    }
    

}
