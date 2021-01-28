using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using Stripe;
using MySql.Data.MySqlClient;
using Server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Newtonsoft.Json.Linq;

namespace Server.Controllers
{
    public class PaymentController : ApiController
    {

        public int Post(Stripepay stripe)
        {
            Jwtmodel jwt = new Jwtmodel();
            int tokenstatus = jwt.jwtCheck(stripe.token);
            if (tokenstatus == 1)
            {
                try
                {
                    BD check = new BD();

                    if ((check.checkinsc(stripe.IDC, stripe.IDEx) == -1)) return -5;
                    BD newbd = new BD();
                    double cost = newbd.getprice(stripe.IDEx);
                    cost = cost * 100;
                    string custo = cost.ToString();
                    String key = stripe.key;
                    long amount = 500;
                    StripeConfiguration.ApiKey = "sk_test_XgPtpokfZ4XKLeqJ1891sNbo00PyeAo83n";
                    var optionscreate = new PaymentIntentCreateOptions

                    {
                        Amount = long.Parse(custo),
                        Currency = "eur",
                        PaymentMethod = key,
                        PaymentMethodTypes = new List<string>
                    {
                      "card",
                     },
                        Confirm = true,
                    };

                    var service = new PaymentIntentService();
                    var ze = service.Create(optionscreate);
                    var options = new PaymentIntentConfirmOptions
                    {
                        PaymentMethod = key,
                    };
                    string s = ze.ToString();
                    string[] words = s.Split(':');
                    string s2 = words[2];
                    string[] payid = s2.Split('\"');
                    try
                    {
                        BD bd = new BD();
                        return bd.InscExcursao(stripe.IDEx, stripe.IDC, payid[1]);
                    }
                    catch(Exception e) { return -4; }

                }
                catch (Exception e)
                {
                    return -3;
                }

            }
            return 0;
                
        }
    }
}