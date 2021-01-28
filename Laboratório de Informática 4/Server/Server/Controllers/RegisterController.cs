using MySql.Data.MySqlClient;
using Server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Server.Controllers
{
    public class RegisterController : ApiController
    {
        public class person
        {
            public string name { get; set; }
        }
        /*public IEnumerable<string> Post()
        {
            return new string[] { "value1", "value2" };
        }*/
        // GET api/values/5
        public int Post([FromBody] User user )
        {  
            string mail = user.Email;
            string nome = user.Nome;
            string pass = user.Pass;
            string n1   = user.Primeiro_nome;
            string n2   = user.Segundo_nome;
            string guia = user.Guia;
            string tel = user.Telemovel;
            BD baseDados = new BD();
            int exists = baseDados.userExists(mail,nome);
            if (exists == 1)
            {
                baseDados.closeConn();
                return 2;
            }
            if (exists == 2)
            {
                baseDados.closeConn();
                return 3;
            }
            int status = baseDados.register(nome,mail,pass,n1,n2,guia,tel);
            if (status == 1) { baseDados.closeConn(); return 1; }
            baseDados.closeConn();
            return -1;
        }

    
    }
}
