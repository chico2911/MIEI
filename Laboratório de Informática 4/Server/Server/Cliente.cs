using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class Cliente : User
    {
        List<int> ExcursaosFeitas { get; set; } //lista dos ids das excursoes
        List<String> TagsInteresse { get; set; } //lista dos interesses do cliente
    }
}