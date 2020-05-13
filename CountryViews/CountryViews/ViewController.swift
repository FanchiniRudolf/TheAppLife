//
//  ViewController.swift
//  CountryViews
//
//  Created by user167967 on 5/13/20.
//  Copyright © 2020 ITESM. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    
    let countryArr = [
        "Argentina", "España", "México", "Estados Unidos"
    ]
    @IBOutlet weak var table: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    //There is a transition
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "countrySegway" {
            let vc = segue.destination as! Country_VC
            let index = table.indexPathForSelectedRow!.row
            vc.countryName = countryArr[index]
        }
    }
    


}

//EXTENSION
extension ViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return countryArr.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "countryCell", for: indexPath)
        
        cell.textLabel?.text = countryArr[indexPath.row]
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        let row = indexPath.row
        
        if row%2 == 0{
            return 52
        }
        
        return 32
    }
    
    //Click on a row
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("Click on: \(indexPath.row), \(countryArr[indexPath.row])")
    }
    
    
    
}

