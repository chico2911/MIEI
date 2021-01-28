using MySql.Data.MySqlClient;
using Newtonsoft.Json.Linq;
using Server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Server.Controllers
{
    public class LoginController : ApiController
    {

        public JObject Post([FromBody] User user)
        {
            JObject jso = new JObject();
            User us = new User();
            
            Jwtmodel jw = new Jwtmodel();
            BD baseDados = new BD();
            us = baseDados.login(user.Email, user.Pass);
            if (us.valido == 1)
            {
                jso["valido"] = JToken.FromObject("1");
                jso["token"] = JToken.FromObject(jw.jwtcreate());
            }
            else
            {
                jso["valido"] = JToken.FromObject("0");
            }
            jso["Userinfo"] = JToken.FromObject(us);
            return jso;
            
        }

     

    }
}
